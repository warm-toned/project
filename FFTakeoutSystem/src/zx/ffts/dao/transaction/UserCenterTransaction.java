package zx.ffts.dao.transaction;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import zx.ffts.dao.SQLExecutor;

public class UserCenterTransaction implements Transactable {

	@Override
	public Object transact(SQLExecutor executor, Object... params)
			throws SQLException {
		String sql = "";
		// 1.获取用户所有订单信息(a.删除所有无效订单)(每个订单要求9条信息)
		sql = "delete from ts_order where ouserid=? and ouuid is null or ocount=0";
		executor.executeUpdate(sql, params[0]);
		// b.获取全部订单列表
		sql = "select * from(select ouuid,ortid,rtname,rtpic,to_char(odate,'yyyy-mm-dd hh24:mi:ss') orderdate,ostatus,oassess,floor((odate+?-sysdate)) timestatus,row_number()over(order by sign(power(10,(abs(floor(odate+?-sysdate))+ostatus-1))-ostatus),odate desc) rm from ts_order o,ts_restaurant r where o.ortid=r.rtid and ouserid=? group by ouuid,ortid,rtname,rtpic,odate,oassess,ostatus) where rm between ? and ?";
		// (每个订单已包含了7条信息:ouuid,ortid,rtname,rtpic,odate,ostatus,timestatus)
		List<Map<String, Object>> list = executor.getMapList(sql, true,
				params[1],params[1], params[0], params[2], params[3]);
		// 2.获取单个订单中的详细信息
		for (Map<String, Object> map : list) {
			Double sum = 0.0;
			String uuid = map.get("ouuid").toString();
			String status = map.get("ostatus").toString();
			String timestatus = map.get("timestatus").toString();
			if (status.equals("1") && timestatus.equals("0")) {
				sql = "select to_char((odate+?),'yyyy-mm-dd hh24:mi:ss') from ts_order where ouuid=?";
				Date date;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.parse(executor.executeScalarString(sql, params[1],
									uuid));
					map.put("deadline", date.getTime());
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
			// 3.获取详细信息
			sql = "select muname,ocount,muprice,(ocount*muprice) msum from ts_order o,ts_menu m where o.omuid=m.muid and ouuid=?";
			List<Map<String, Object>> sublist = executor.getMapList(sql, true,
					uuid);
			// 4.计算单个订单金额
			for (Map<String, Object> map2 : sublist) {
				sum = sum + ((Number) (map2.get("msum"))).doubleValue();
			}
			// 5.完善单个订单信息(第八条:)
			map.put("details", sublist);
			// 第九条:sum
			map.put("sum", sum);
		}
		return list;
	}
	
}
