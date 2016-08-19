package zx.ffts.dao.pain;

import zx.ffts.dao.DataAccessObject;

public class RestaurantDao extends DataAccessObject {

	public Integer setApply(String rtname, String rtaddr, Integer userid,
			String path, String rtcontent, String rtonbuz) {
		String sql = "insert into ts_restaurant values(ts_restaurant_seq.nextval,?,?,?,?,?,sysdate,?,1)";
		return executeUpdate(sql, rtname, rtaddr, userid, path, rtcontent,
				rtonbuz);
	}

	public Boolean hasApply(Integer userid) {
		String sql = "select count(*) from ts_restaurant where rtowner=? and rtstatus=1";
		return executeScalarInteger(sql, userid) > 0;
	}
}
