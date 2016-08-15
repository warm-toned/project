package zx.ffts.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import zx.ffts.dao.transaction.Transactable;
import zx.ffts.utils.DbUtils;
import zx.ffts.utils.ExtenseQueryRunner;
import zx.ffts.utils.PageBean;

/**
 * 继承与执行者类并实现了其中的方法，并提供了对事务的支持
 * 
 * @author Pain
 * 
 */
public class DataAccessObject extends SQLExecutor {

	/**
	 * 用于处理sql语句的runner对象
	 */
	private QueryRunner qr = new ExtenseQueryRunner();

	@Override
	public JSONObject getJsonObject(String sql, boolean toLower,
			Object... params) {
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), params);
			if (toLower)
				return JSONObject.fromObject(toLowerCaseOfColumnName(map));
			else
				return JSONObject.fromObject(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Map<String, Object> getObject(String sql, boolean toLower,
			Object... params) {
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), params);
			if (toLower)
				return toLowerCaseOfColumnName(map);
			else
				return map;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public JSONArray getJsonArray(String sql, boolean toLower, Object... params) {
		try {
			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), params);
			if (toLower)
				return JSONArray.fromObject(toLowerCaseOfColumnName(list));
			else
				return JSONArray.fromObject(list);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Map<String, Object>> getMapList(String sql, boolean toLower,
			Object... params) {
		try {
			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), params);
			if (toLower)
				return toLowerCaseOfColumnName(list);
			else
				return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Integer executeScalarInteger(String sql, Object... params) {
		String number = executeScalarString(sql, params);
		return number == null ? 0 : new Integer(number);
	}

	@Override
	public Double executeScalarDouble(String sql, Object... params) {
		String number = executeScalarString(sql, params);
		return number == null ? 0 : new Double(number);
	}

	@Override
	public String executeScalarString(String sql, Object... params) {
		try {
			Object obj = qr.query(sql, new ScalarHandler(), params);
			return obj == null ? null : obj.toString();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int executeUpdate(String sql) {
		try {
			return qr.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int executeUpdate(String sql, Object param) {
		try {
			return qr.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int executeUpdate(String sql, Object... params) {
		try {
			return qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T> T getEntity(String sql, T t, Object... params) {
		try {
			return qr.query(sql, new BeanHandler<T>((Class<T>) t.getClass()),
					params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T> List<T> getEntities(String sql, T t, Object... params) {
		try {
			return qr.query(sql,
					new BeanListHandler<T>((Class<T>) t.getClass()), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Map<String, Object>> getPageBean(String sql,
			String[] partition, String[] order, String where,
			Integer currentPage, Integer pageSize, Object... params) {
		PageBean<Map<String, Object>> bean = new PageBean<Map<String, Object>>(
				currentPage, pageSize);
		List<Object> paramList = new ArrayList<Object>();
		if (params != null && params.length != 0) {
			paramList.addAll(Arrays.asList(params));
		}
		// 查询所有行数
		String countSql = getCountSql(sql, where);
		bean.setTotalCounts(executeScalarInteger(countSql, paramList.toArray()));
		// 查询对应页的数据
		String fullSql = getFullSql(sql, partition, order, where);
		Integer begin = (currentPage - 1) * pageSize + 1;
		paramList.add(begin);
		paramList.add(currentPage * pageSize);
		bean.setBeanList(getMapList(fullSql, true, paramList.toArray()));
		return bean;
	}

	/**
	 * 执行事务
	 * 
	 * @param trans
	 *            :实现了Transactable接口的任何类的对象
	 * @param params
	 *            :Transactable接口的transact方法要求的参数列表
	 * @return
	 */
	public Object doTransaction(Transactable trans, Object... params) {
		try {
			DbUtils.beginTransaction();
			Object object = trans.transact(this, params);
			DbUtils.commitTransaction();
			return object;
		} catch (SQLException e) {
			try {
				DbUtils.rollbackTransaction();
				return "Fail:" + e.getMessage();
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	/**
	 * 用于转换大写的键为小写
	 * 
	 * @param list
	 * @return
	 */
	private List<Map<String, Object>> toLowerCaseOfColumnName(
			List<Map<String, Object>> list) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> m = new HashMap<String, Object>();
			Set<String> keys = map.keySet();
			for (String key : keys) {
				m.put(key.toLowerCase(), map.get(key));
			}
			result.add(m);
		}
		return result;
	}

	/**
	 * 用于转换大写的键为小写
	 * 
	 * @param map
	 * @return
	 */
	private Map<String, Object> toLowerCaseOfColumnName(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			result.put(key.toLowerCase(), map.get(key));
		}
		return result;
	}

	private String getCountSql(String sql, String where) {
		StringBuilder sb = new StringBuilder("select count(*) ");
		sb.append(sql.substring(sql.toLowerCase().indexOf("from")));
		if (!sql.toLowerCase().contains("where")) {
			sb.append(" where 1=1");
		}
		if (where != null && !where.isEmpty()) {
			sb.append(where);
		}
		return sb.toString();
	}

	private String getFullSql(String sql, String[] partition, String[] order,
			String where) {
		StringBuilder sb = new StringBuilder("select * from ( ");
		sb.append(sql.substring(0, sql.indexOf("from")));
		if (order != null && order.length != 0) {
			sb.append(",row_number()over(");
			if (partition != null && partition.length != 0) {
				sb.append("partition by");
				sb.append(Arrays.toString(partition).replace("[", " ")
						.replace("]", " "));
			}
			sb.append("order by");
			sb.append(Arrays.toString(order).replace("[", " ")
					.replace("]", " "));
			sb.append(") rw ");
		} else {
			sb.append(",rownum rw ");
		}
		sb.append(sql.substring(sql.indexOf("from")));
		if (!sql.toLowerCase().contains("where")) {
			sb.append(" where 1=1");
		}
		if (where != null && !where.isEmpty()) {
			sb.append(where);
		}
		sb.append(") db where db.rw between ? and ?");
		return sb.toString();
	}
}
