package zx.ffts.dao.chenkai;

import java.util.List;
import java.util.Map;

import zx.ffts.dao.DataDao;
import zx.ffts.domain.chenkai.TsOrder;


public class TsOrderDao extends DataDao {
	
	//查询所有订单
	public List<Map<String, Object>> getOrderList(Integer nowPage,Integer pageSize){	
		String sql="select * from (select t.*,rownum rn from(" +
				"select a.oid,a.ouserid,a.omuid,a.ortid,a.ocount," +
				"a.osender,a.ouuid,a.ostatus,to_char(a.odate,'yyyy-mm-dd hh24:mi:ss')as odate," +
				"b.username as ousername,c.rtname as ortname,d.muname as omuname," +
				"e.username as osendername from ts_order a " +
				"inner join ts_user b on a.ouserid =b.userid " +
				"inner join ts_restaurant c on a.ortid=c.rtid " +
				"inner join ts_menu d on a.omuid=d.muid " +
				"inner join ts_user e on a.osender=e.userid order by oid)t) where rn between ? and ?";	
		//List<TsOrder> list=getEntities(sql,rest,(((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		List<Map<String, Object>> list=getMapList(sql, (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	//查询所有订单
	public List<TsOrder>  getAllOrder(){
		String sql="select * from ts_order order by oid";	
		TsOrder order=new TsOrder();
		List<TsOrder>  list=getEntities(sql, order);
		return list;
	}
	
	//通过id查询订单
	public TsOrder findOrderById(Integer id){
		TsOrder order=new TsOrder();
		String sql="select * from ts_order a where oid=?";
		TsOrder ts=getEntity(sql, order, id);
		return ts;
	}
	
	//删除订单
	public Integer deleteOrder(Integer id){
		String sql="delete from ts_order where oid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改订单
	public Integer updateOrder(Integer ouserid,Integer omuid,Integer ortid,Integer ocount,Integer osender,String odate,String ouuid,Integer ostatus,Integer oid){
		String sql="update ts_order set ouserid=?,omuid=?,ortid=?,ocount=?,osender=?,odate=to_date('"+odate+"','yyyy-MM-dd hh24:mi:ss'),ouuid=?,ostatus=? where oid=? ";
		Integer i=update(sql,ouserid,omuid,ortid,ocount,osender,ouuid,ostatus,oid);
		return i;
	}
	//查询所有订单数量
	public Integer orderCount(){
		String sql="select count(*) as cou from ts_order";
		Integer i=scalarNumber(sql);
		return i;
	}
	
	//下载所有订单
	public List<Map<String, Object>> WriteOrder(){
		String sql="select a.oid,a.ouserid,a.omuid,a.ortid,a.ocount," +
				"a.osender,a.ouuid,a.ostatus,to_char(a.odate,'yyyy-mm-dd hh24:mi:ss')as odate," +
				"b.username as ousername,c.rtname as ortname,d.muname as omuname," +
				"e.username as osendername from ts_order a " +
				"inner join ts_user b on a.ouserid =b.userid " +
				"inner join ts_restaurant c on a.ortid=c.rtid " +
				"inner join ts_menu d on a.omuid=d.muid " +
				"inner join ts_user e on a.osender=e.userid order by oid";
		List<Map<String, Object>> list=getMapList(sql);
		return list;
	}
}
