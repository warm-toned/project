package zx.ffts.dao.xiong;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSONArray;

import zx.ffts.dao.DataAccessObject;
import zx.ffts.utils.DbUtils;

public class OrderFunctionDao extends DataAccessObject {

	@Test
	public void test() {
	}

	public Integer orderCount(Integer userid, Integer rtid, Integer muid) {
		String sql = "select ocount from ts_order where ouserid=? and ortid=? and omuid=? and ostatus=0";
		return executeScalarInteger(sql, userid, rtid, muid);
	}

	public List<Map<String, Object>> getUnorderedCount(Integer userid,
			Integer rtid) {
		String sql = "select ocount,omuid from ts_order where ouserid=? and ortid=? and ostatus=0";
		return getMapList(sql, true, userid, rtid);
	}

	public JSONArray getCart(Integer userid, Integer rtid) {
		String sql = "select muname,muprice,ocount from ts_order o,ts_menu m where o.omuid=m.muid and ouserid=? and ortid=? and ostatus=0 and ocount>0";
		return getJsonArray(sql, true, userid, rtid);
	}

	public List<Map<String, Object>> getCartList(Integer userid, Integer rtid) {
		String sql = "select muname,muprice,ocount from ts_order o,ts_menu m where o.omuid=m.muid and ouserid=? and ortid=? and ostatus=0 and ocount>0";
		return getMapList(sql, true, userid, rtid);
	}

	public String setOrder(Integer userid, Integer rtid) {
		String sql = "update ts_order set odate=sysdate,ouuid=?,ostatus=1 where ouserid=? and ortid=? and ostatus=0";
		String uuid = DbUtils.getUUID();
		executeUpdate(sql, uuid, userid, rtid);
		return uuid;
	}

	public Double getOrderMoney(String uuid) {
		String sql = "select sum(ocount*muprice) from ts_order o,ts_menu m where o.omuid=m.muid and ouuid=? and ostatus=1";
		return executeScalarDouble(sql, uuid);
	}

	public String getDeadline(Double ti, String uuid) {
		Double timespan = (ti / 60 / 24);
		String time = null;
		String sql = "select floor((odate+?)-sysdate) from ts_order where ouuid=?";
		Integer state = executeScalarInteger(sql, timespan, uuid);
		if (state < 0) {
			sql = "delete from ts_order where ouuid=?";
			executeUpdate(sql, uuid);
		} else {
			sql = "select to_char((odate+?),'yyyy-mm-dd hh24:mi:ss') from ts_order where ouuid=?";
			time = executeScalarString(sql, timespan, uuid);
		}
		return time;
	}

	public JSONArray getOrderInfo(String uuid) {
		String sql = "select rtname,rtpic,r.rtid,muname,muprice,ocount,ostatus,to_char(odate,'yyyy-mm-dd hh24:mi:ss') ordertime from ts_order o,ts_restaurant r,ts_menu m where o.omuid=m.muid and o.ortid=r.rtid and ouuid=?";
		return getJsonArray(sql, true, uuid);
	}

	public Integer getOneOrderNumber(Integer userid, Integer muid) {
		String sql = "select ocount from ts_order where ouserid=? and omuid=? and ostatus=0";
		return executeScalarInteger(sql, userid, muid);
	}

	public Double getHaoPing(Integer muid) {
		String sql = "select count(*) from ts_menumsg where mmmuid=?";
		Integer number = executeScalarInteger(sql, muid);
		if (number != 0) {
			sql = "select (select count(*) from ts_menumsg where mmmuid=? and mmscore>=4)/(select count(*) from ts_menumsg where mmmuid=?) from dual";
			return executeScalarDouble(sql, muid, muid);
		} else {
			return -1.00;
		}
	}

	public Double getRestaurantScore(Integer rtid) {
		String sql = "select count(*) from ts_message where mrtid=?";
		Integer number = executeScalarInteger(sql, rtid);
		if (number != 0) {
			sql = "select avg(mscore) from ts_message where mrtid=?";
			return executeScalarDouble(sql, rtid);
		} else {
			return 5.0;
		}
	}
	
	public void getUserInfoToList(List<Map<String,Object>> list){
		String sql = "select username,photo from ts_user where userid=?";
		for (Map<String, Object> map : list) {
			Map<String,Object> map2 = getObject(sql, true, map.get("muserid"));
			map.putAll(map2);
		}
	}
}
