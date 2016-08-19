package zx.ffts.dao.pain;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import zx.ffts.dao.DataAccessObject;

public class SenderDao extends DataAccessObject {

	public JSONArray getUsableOrders(Integer page, Integer size) {
		// 获取全部可接订单(分页)
		String sql = "select * from (select rtname,rtpic,ouuid,ouserid,photo,realname,address,tel,gender,ortid,ostatus,to_char(odate,'yyyy-mm-dd hh24:mi:ss') otime,row_number()over(order by odate asc) rw from ts_order o,ts_restaurant r,ts_user u where u.userid=o.ouserid and r.rtid=o.ortid and osender is null and ouuid is not null and ostatus=3 group by ouuid,ouserid,ortid,ostatus,odate,rtname,rtpic,realname,address,tel,gender,photo) where rw between ? and ?";
		List<Map<String, Object>> list = getMapList(sql, true, ((page - 1)
				* size + 1), (page * size));
		// 获取每单的信息
		for (Map<String, Object> map : list) {
			Double sum = 0.0;
			String uuid = map.get("ouuid").toString();
			// 3.获取详细信息
			sql = "select muname,ocount,muprice,(ocount*muprice) msum from ts_order o,ts_menu m where o.omuid=m.muid and ouuid=?";
			List<Map<String, Object>> sublist = getMapList(sql, true, uuid);
			// 4.计算单个订单金额
			for (Map<String, Object> map2 : sublist) {
				sum = sum + ((Number) (map2.get("msum"))).doubleValue();
			}
			// 5.完善单个订单信息(第八条:)
			map.put("details", sublist);
			// 第九条:sum
			map.put("sum", sum);
		}
		return JSONArray.fromObject(list);
	}

	public JSONArray getSavedOrder(Integer userid) {
		// 获取全部已接订单
		String sql = "select rtname,rtpic,ouuid,ouserid,photo,realname,address,tel,gender,ortid,ostatus,to_char(odate,'yyyy-mm-dd hh24:mi:ss') otime from ts_order o,ts_restaurant r,ts_user u where u.userid=o.ouserid and r.rtid=o.ortid and osender=? and ouuid is not null and ostatus=4 group by ouuid,ouserid,ortid,ostatus,odate,rtname,rtpic,realname,address,tel,gender,photo";
		List<Map<String, Object>> list = getMapList(sql, true, userid);
		// 获取每单的信息
		for (Map<String, Object> map : list) {
			Double sum = 0.0;
			String uuid = map.get("ouuid").toString();
			// 3.获取详细信息
			sql = "select muname,ocount,muprice,(ocount*muprice) msum from ts_order o,ts_menu m where o.omuid=m.muid and ouuid=?";
			List<Map<String, Object>> sublist = getMapList(sql, true, uuid);
			// 4.计算单个订单金额
			for (Map<String, Object> map2 : sublist) {
				sum = sum + ((Number) (map2.get("msum"))).doubleValue();
			}
			// 5.完善单个订单信息(第八条:)
			map.put("details", sublist);
			// 第九条:sum
			map.put("sum", sum);
		}
		return JSONArray.fromObject(list);
	}

	public String getPageCounts() {
		String sql = "select count(*) from (select ouuid from ts_order where osender is null and ouuid is not null and ostatus=3 group by ouuid)";
		return executeScalarString(sql);
	}

	public Integer getSavedNumbers(Integer userid) {
		String sql = "select count(*) from (select ouuid from ts_order where osender = ? and ouuid is not null and ostatus=4 group by ouuid)";
		return executeScalarInteger(sql, userid);
	}

	public Integer saveOrder(String uuid, Integer userid) {
		String sql = "select count(*) from ts_order where ouuid=? and osender is null and ostatus=3 group by ouuid";
		Integer line = executeScalarInteger(sql, uuid);
		if (line > 0) {
			sql = "update ts_order set ostatus=4,osender=? where ouuid=? and ostatus=3";
			return executeUpdate(sql, userid, uuid);
		} else {
			return -1;
		}
	}

	public Integer received(String uuid) {
		String sql = "update ts_order set ostatus=5 where ouuid=?";
		return executeUpdate(sql, uuid);
	}

	public JSONArray getSendedOrders(Integer page2, Integer size2,
			Integer userid) {
		// 获取全部可接订单(分页)
		String sql = "select * from (select rtname,rtpic,ouuid,ouserid,photo,realname,address,tel,gender,ortid,ostatus,to_char(odate,'yyyy-mm-dd hh24:mi:ss') otime,row_number()over(order by odate asc) rw from ts_order o,ts_restaurant r,ts_user u where u.userid=o.ouserid and r.rtid=o.ortid and osender=? and ouuid is not null and ostatus=5 group by ouuid,ouserid,ortid,ostatus,odate,rtname,rtpic,realname,address,tel,gender,photo) where rw between ? and ?";
		List<Map<String, Object>> list = getMapList(sql, true, userid,
				((page2 - 1) * size2 + 1), (page2 * size2));
		// 获取每单的信息
		for (Map<String, Object> map : list) {
			Double sum = 0.0;
			String uuid = map.get("ouuid").toString();
			// 3.获取详细信息
			sql = "select muname,ocount,muprice,(ocount*muprice) msum from ts_order o,ts_menu m where o.omuid=m.muid and ouuid=?";
			List<Map<String, Object>> sublist = getMapList(sql, true, uuid);
			// 4.计算单个订单金额
			for (Map<String, Object> map2 : sublist) {
				sum = sum + ((Number) (map2.get("msum"))).doubleValue();
			}
			// 5.完善单个订单信息(第八条:)
			map.put("details", sublist);
			// 第九条:sum
			map.put("sum", sum);
		}
		return JSONArray.fromObject(list);
	}

	public Integer getSendCounts(Integer userid) {
		String sql = "select count(*) from (select ouuid from ts_order where ouuid is not null and ostatus=5 and osender=? group by ouuid)";
		return executeScalarInteger(sql, userid);
	}
}
