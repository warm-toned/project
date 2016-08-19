package zx.ffts.dao.chenkai;

import java.util.List;

import zx.ffts.dao.DataDao;
import zx.ffts.domain.chenkai.TsPay;

public class TsPayDao extends DataDao {
	
	//查询所有支付方式
	public List<TsPay>  getPayList(Integer nowPage,Integer pageSize){
		String sql="select * from (select t.*,rownum rn from(select * from ts_pay order by pid)t)where rn between ? and ?";	
		TsPay  pay=new TsPay();
		List<TsPay>  list=getEntities(sql,pay , (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	
	//通过id查询支付方式
	public TsPay findPayById(Integer id){
		TsPay pay=new TsPay();
		String sql="select * from ts_pay where pid=?";
		TsPay ts=getEntity(sql, pay, id);
		return ts;
	}
	//添加支付方式
	public Integer addPay(String ptype,Integer poid){
		String sql="insert into ts_pay values(ts_pay_seq,?,?)";
		Integer i=update(sql,ptype,poid);
		return i;
	}
	//删除支付方式
	public Integer deletePay(Integer id){
		String sql="delete from ts_pay where pid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改支付方式
	public Integer updatePay(String ptype,Integer pid){
		String sql="update ts_pay set ptype=? where pid=? ";
		Integer i=update(sql,ptype,pid);
		return i;
	}
	//查询所有支付方式数量
	public Integer payCount(){
		String sql="select count(*) as cou from ts_pay";
		Integer i=scalarNumber(sql);
		return i;
	}
}
