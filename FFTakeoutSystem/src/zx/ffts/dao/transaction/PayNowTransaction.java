package zx.ffts.dao.transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import zx.ffts.dao.SQLExecutor;

public class PayNowTransaction implements Transactable {

	@Override
	public Object transact(SQLExecutor executor, Object... params)
			throws SQLException {
		// 1.修改订单状态
		String sql = "update ts_order set ostatus=2 where ouuid=? and ostatus=1";
		int line = executor.executeUpdate(sql, params[0]);
		if (line < 1) {
			throw new SQLException("已经支付！");
		}
		// 2.减少用户余额
		sql = "select balance-? money from ts_user where userid=(select ouserid from ts_order where ouuid=? group by ouserid)";
		Double money = executor.executeScalarDouble(sql, params[1], params[0]);
		if (money < 0) {
			throw new SQLException("余额不足！");
		}
		sql = "update ts_user set balance = balance-? where userid=(select ouserid from ts_order where ouuid=? group by ouserid)";
		executor.executeUpdate(sql, params[1], params[0]);
		// 3.统计各菜的销售额
		sql = "select omuid,ocount from ts_order where ouuid = ?";
		List<Map<String, Object>> list = executor.getMapList(sql, true,
				params[0]);
		for (Map<String, Object> map : list) {
			sql = "update ts_menu set musale=musale+? where muid=?";
			executor.executeUpdate(sql, map.get("ocount"), map.get("omuid"));
		}
		return "OK";
	}

}
