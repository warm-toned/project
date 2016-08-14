package zx.ffts.dao.transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import zx.ffts.dao.SQLExecutor;

public class GetAssessInfoTransaction implements Transactable {

	@Override
	public Object transact(SQLExecutor executor, Object... params)
			throws SQLException {
		String sql = "";
		// 返回一个Map
		Integer rtid = executor.executeScalarInteger(
				"select ortid from ts_order where ouuid=? group by ortid",
				params[1]);
		sql = "select ouuid,ortid,rtname,rtpic from ts_order o,ts_restaurant r where o.ortid=r.rtid and ouserid=? and r.rtid=? and ouuid =? and ouuid is not null group by ouuid,ortid,rtname,rtpic";
		Map<String, Object> mapShop = executor.getObject(sql, true, params[0],
				rtid, params[1]);
		// 根据当前对象取到uuid
		String uuid = mapShop.get("ouuid").toString();
		// 然后根据uuid取到一个List<Map<String, Object>>
		sql = "select muname,ocount,muprice,muid from ts_order o,ts_menu m where o.omuid=m.muid and ouuid=? order by oid";
		List<Map<String, Object>> menuList = executor.getMapList(sql, true,
				uuid);
		// 将list集合存到mapShop中
		mapShop.put("menuList", menuList);
		return mapShop;
	}

}
