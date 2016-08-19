package zx.ffts.dao.transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import zx.ffts.dao.SQLExecutor;

public class SetAssessTransaction implements Transactable {

	@Override
	public Object transact(SQLExecutor executor, Object... params)
			throws SQLException {
		String sql = "";
		// 给商店评论ts_message_seq.nextval,userid,rtid,content,score,sysdate);
		sql = "insert into ts_message values(ts_message_seq.nextval,?,?,?,sysdate,?)";
		executor.executeUpdate(sql, params[1], params[2], params[3], params[4]);
		// 得到需要评价的菜单id
		sql = "select omuid from ts_order o where ouuid=? order by oid";
		List<Map<String, Object>> list = executor.getMapList(sql, true,
				params[0]);
		List<Integer> menuScore = (List<Integer>) params[5];
		List<String> menuAssess = (List<String>) params[6];
		int i = 0;
		for (Map<String, Object> map : list) {
			if (menuScore.get(i++) == 0) {
				continue;
			}
			// 给菜单评论values (mmid,mmuserid,mmmuid,content，mmscore,mmdate);
			sql = "insert into ts_menumsg values (ts_menumsg_seq.nextval,?,?,?,?,sysdate)";
			executor.executeUpdate(sql, params[1], map.get("omuid"),
					menuAssess.get(i - 1), menuScore.get(i - 1));
		}
		// 修改订单中评论状态
		sql = "update ts_order set oassess=1 where ouuid=?";
		executor.executeUpdate(sql, params[0]);
		sql = "update ts_user set score=score+? where userid=?";
		executor.executeUpdate(sql, params[7], params[1]);
		return null;
	}

}
