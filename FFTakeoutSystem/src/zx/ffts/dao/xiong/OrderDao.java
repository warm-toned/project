package zx.ffts.dao.xiong;

import java.util.List;
import java.util.Map;
import zx.ffts.dao.DataDao;

public class OrderDao extends DataDao {

	/**
	 * 点击加号是将菜加入到购物车中
	 * 
	 * @param userid
	 * @param menuid
	 * @param dianid
	 * @return
	 */
	public int addOrder(Integer userid, Integer menuid, Integer dianid) {
		String sql = "select count(*) from ts_order where ouserid=? and omuid=? and ortid=? and ostatus=0";
		int num = scalarNumber(sql, userid, menuid, dianid);
		if (num != 0) {
			String updatesql = "update ts_order set ocount=ocount+1 where omuid=? and ortid=?";
			return update(updatesql, menuid, dianid);
		} else {
			String addsql = "insert into ts_order values(ts_order_seq.nextval,?,?,?,1,2,sysdate,null,0,0)";
			return update(addsql, userid, menuid, dianid);
		}
	}

	/**
	* 在菜单中减数量
	* @param userid
	* @param menuid
	* @param dianid
	* @return
	*/
	public int minusOrder(Integer userid, Integer menuid, Integer dianid){
		String sql="update ts_order set ocount=ocount-1 where  ouserid=? and omuid=? and ortid=? ";
		return update(sql, userid, menuid, dianid);
	}

	/**
	 * 点击减号将菜移除订单中
	 * 
	 * @param userid
	 * @param menuid
	 * @param dianid
	 * @return
	 */
	public int deleteOrder(Integer userid, Integer menuid, Integer dianid) {
		String sql = "select count(*) from ts_order where ouserid=? and omuid=? and ortid=? and ostatus=0";
		int num = scalarNumber(sql, userid, menuid, dianid);
		if (num != 0) {
			String updatesql = "update ts_order set ocount=ocount-1 where omuid=? and ortid=?";
			return update(updatesql, menuid, dianid);
		} else {
			String addsql = "delete from  ts_order where ouserid=? and omuid=? and ortid=? and ostatus=0";
			return update(addsql, userid, menuid, dianid);
		}
	}
	
	/**
	 * 删除小于1的订单
	 * @param userid
	 * @param menuid
	 * @param dianid
	 * @return
	 */
	public int deleteShp(Integer userid, Integer menuid, Integer dianid){
		String sql="delete from  ts_order where ouserid=? and omuid=? and ortid=? and ostatus=0";
			return update(sql, userid, menuid, dianid);
	}
   /**
	 * 点了去结算时展现所有的订单信息
	 * 
	 * @param userid
	 * @param restuantid
	 * @return
	 */
	public Map<String, Object> getDetail(Integer userid,
			Integer restuantid) {
		String sql = "select oid,ocount,muname,muprice,rtname,username,gender,tel,address,rtid from ts_order o  "
				+ "inner join ts_menu m  on o.omuid=m.muid "
				+ " inner join ts_restaurant r on o.ortid=r.rtid "
				+ "inner join ts_user u on o.ouserid=u.userid "
				+ "  where o.ouserid=? and r.rtid=? and ostatus=0";
		return getObject(sql, userid,restuantid);
	}

	/**
	 * 查看我的订单
	 * @param userid
	 * @param shopid
	 * @return
	 */
	public List<Map<String, Object>> getCar(Integer userid, Integer shopid) {
		String sql = "select muname,omuid,ortid,muprice,ocount,rtname from ts_order o "
				+ "inner join ts_menu m on m.muid=o.omuid "
				+ "inner join ts_restaurant r on o.ortid=r.rtid "
				+ "where ouserid=?  and  ostatus=0 and r.rtid=? and ocount>=1";
		return getMapList(sql, userid, shopid);
	}
	
	/**
	 * 清空购物车
	 * @param userid
	 * @param shopid
	 * @return
	 */
	public int clearCart(Integer userid,Integer shopid){
		String sql="delete from ts_order where ouserid=? and ortid=? and ostatus=0";
		return update(sql, userid,shopid); 
	}
	
	/**
	 * 修改订单中的状态
	 * @param userid
	 * @param shopid
	 * @return
	 */
	public int updateStatus(Integer userid,Integer shopid){
		String sql="update ts_order set ostatus=1 where ouserid=? and ortid=?";
		return update(sql, userid,shopid);
	}
	
	/**
	 * 修改用户的余额
	 * @param userid
	 * @param money
	 * @return
	 */
	public int updateMoney(Integer userid,Integer money){
		String sql="update ts_order set balance=balance-? where userid=?";
		return update(sql, money,userid);
	}
	
	/**
	 * 修改当前菜的销售量
	 * @param userid
	 * @param shopid
	 * @param menuid
	 * @return
	 */
	public int updateSale(Integer userid,Integer shopid,Integer menuid){
		String sql="update ts_menu set musale=musale+(select ocount from ts_order where ouserid=? and omuid=? and ortid=?) where muid=? and murtid=?";
		return update(sql, userid,menuid,shopid,menuid,shopid);
	}
	
	/**
	 * 根据商店id查商店信息
	 * @param shopid
	 * @return
	 */
	public Map<String, Object> getShop(Integer shopid){
		String sql="select rtname,rtaddr,rtpic,tel from ts_restaurant r inner join ts_user u on r.rtowner=u.userid  where rtid=?";
		return getObject(sql, shopid);
	}
}
