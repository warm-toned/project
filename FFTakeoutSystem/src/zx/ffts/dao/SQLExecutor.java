package zx.ffts.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import zx.ffts.utils.PageBean;

/**
 * 执行者类
 * 
 * @author Pain
 * 
 */
public abstract class SQLExecutor {

	/**
	 * 返回JSONObject类型的结果，本质是对Map<String, Object>的json封装
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param toLower
	 *            : 是否将键转为小写键
	 * @param params
	 *            : 按照SQL语句中?的对应顺序的可变参数列表
	 * @return JSONObject类型的结果
	 */
	public abstract JSONObject getJsonObject(String sql, boolean toLower,
			Object... params);

	/**
	 * 返回Map<String, Object>，本质是一个对象
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param toLower
	 *            : 是否将键转为小写键
	 * @param params
	 *            : 按照SQL语句中?的对应顺序的可变参数列表
	 * @return Map<String, Object>类型的结果集
	 */
	public abstract Map<String, Object> getObject(String sql, boolean toLower,
			Object... params);

	/**
	 * 返回JSONArray，本质是对List<Map<String, Object>>的json封装
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param toLower
	 *            : 是否将键转为小写键
	 * @param params
	 *            : 按照SQL语句中?的对应顺序的可变参数列表
	 * @return JSONArray类型的结果
	 */
	public abstract JSONArray getJsonArray(String sql, boolean toLower,
			Object... params);

	/**
	 * 返回一个List<Map<String, Object>>类型的结果集，本质是一组对象
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param toLower
	 *            : 是否将键转为小写键
	 * @param params
	 *            : 按照SQL语句中?的对应顺序的可变参数列表
	 * @return List<Map<String, Object>>类型的结果集
	 */
	public abstract List<Map<String, Object>> getMapList(String sql,
			boolean toLower, Object... params);

	/**
	 * 返回单行单列整数结果
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param params
	 *            : 按照SQL语句中?的对应顺序的可变参数列表
	 * @return 单行单列整数结果
	 */
	public abstract Integer executeScalarInteger(String sql, Object... params);

	/**
	 * 返回单行单列浮点类型的结果
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param params
	 *            : 按照SQL语句中?的对应顺序的可变参数列表
	 * @return 单行单列double结果
	 */
	public abstract Double executeScalarDouble(String sql, Object... params);

	/**
	 * 返回单行单列结果，但是需要自己转换类型，使用new的方式
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param params
	 *            : 按照SQL语句中?的对应顺序的可变参数列表
	 * @return 单行单列字符串类型的结果
	 */
	public abstract String executeScalarString(String sql, Object... params);

	/**
	 * 执行没有参数的DML
	 * 
	 * @param sql
	 *            : SQL语句
	 * @return 受影响的行数
	 */
	public abstract int executeUpdate(String sql);

	/**
	 * 执行带一个参数的DML
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param param
	 *            : 要求的参数
	 * @return 受影响的行数
	 */
	public abstract int executeUpdate(String sql, Object param);

	/**
	 * 执行增加、删除、修改的sql语句，参数按照sql语句中问号的顺序依次书写
	 * 例如:update(sql,username,pwd,info....)，可以写N个参数 返回被修改的行数
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param params
	 *            : 按照sql语句中?的对应顺序的可变参数列表
	 * @return 受影响的行数
	 */
	public abstract int executeUpdate(String sql, Object... params);

	/**
	 * 获取实体对象
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param t
	 *            : 要获取的实体类对象(任意)
	 * @param params
	 *            : 按照sql语句中?的对应顺序的可变参数列表
	 * @return 实体对象
	 */
	public abstract <T> T getEntity(String sql, T t, Object... params);

	/**
	 * 获取实体集合
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param t
	 *            : 要获取的实体类对象(任意)
	 * @param params
	 *            : 按照sql语句中?的对应顺序的可变参数列表
	 * @return 实体集合
	 */
	public abstract <T> List<T> getEntities(String sql, T t, Object... params);

	/**
	 * 获取非框架分页对象及页面数据
	 * 
	 * @param sql
	 *            : SQL语句
	 * @param partition
	 *            : 要分组的列名数组
	 * @param order
	 *            : 要排序的列名数组
	 * @param where
	 *            : 要条件查询的语句
	 * @param currentPage
	 *            : 当前页码
	 * @param pageSize
	 *            : 页面大小
	 * @param params
	 *            : 条件查询的参数列表，如果没有条件请什么都不要传
	 * @return
	 */
	public abstract PageBean<Map<String, Object>> getPageBean(String sql,
			String[] partition, String[] order, String where,
			Integer currentPage, Integer pageSize, Object... params);
}
