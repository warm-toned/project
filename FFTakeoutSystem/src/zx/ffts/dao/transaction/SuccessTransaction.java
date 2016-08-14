package zx.ffts.dao.transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import zx.ffts.dao.SQLExecutor;

public class SuccessTransaction implements Transactable {

	@Override
	public Object transact(SQLExecutor executor, Object... params)
			throws SQLException {
		String sql="";
		//给商店评论ts_message_seq.nextval,userid,rtid,content,score,sysdate);
		sql="insert into ts_message values(ts_message_seq.nextval,?,?,?,?,sysdate) ";
		executor.executeUpdate(sql, params[1],params[2],params[3],params[4]);
		//得到需要评价的菜单id
		sql="select muid from ts_order o,ts_menu m where o.omuid=m.muid and ouuid=?";
		List<Map<String, Object>> list=executor.getMapList(sql, true, params[0]);
		for (Map<String, Object> map : list) {
			Integer muid=Integer.parseInt(map.get("muid").toString());
			//给菜单评论values (mmid,mmuserid,mmmuid,content，mmscore,mmdate);
			sql="insert into ts_menumsg values (ts_menumsg_seq.nextval,?,?,?,?,sysdate)";
			executor.executeUpdate(sql, params[1],muid,params[5],params[6]);
			//修改订单中评论状态
			sql="update ts_order set oassess=1 where ouuid=? and omuid=?";
			executor.executeUpdate(sql, params[0],muid);
		}
		return null;
	}

}
