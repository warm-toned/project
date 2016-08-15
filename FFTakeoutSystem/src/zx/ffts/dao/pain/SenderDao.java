package zx.ffts.dao.pain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import zx.ffts.dao.DataAccessObject;

public class SenderDao extends DataAccessObject {

	public JSONArray getUsableOrders(Integer page, Integer size) {
		// 获取全部可接订单(分页)
		String sql = "select * from (select ouuid,ouserid,ortid,ostatus,count(*),row_number()over(order by odate desc) rw from ts_order where osender is null group by ouuid,ouserid,ortid,ostatus,odate) where rw between ? and ?";
		List<Map<String, Object>> list = getMapList(sql, true, ((page - 1)
				* size + 1), (page * size));
		// 获取每单的信息
		for (Map<String, Object> map : list) {
			Double sum = 0.0;
			String uuid = map.get("ouuid").toString();
			String status = map.get("ostatus").toString();
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
}
