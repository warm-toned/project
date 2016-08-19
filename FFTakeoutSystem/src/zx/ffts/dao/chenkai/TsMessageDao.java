package zx.ffts.dao.chenkai;

import java.util.List;

import zx.ffts.dao.DataDao;
import zx.ffts.domain.chenkai.TsMessage;

public class TsMessageDao extends DataDao {

	//查询所有店铺评论
	public List<TsMessage>  getMessageList(Integer nowPage,Integer pageSize){
		String sql="select * from (select t.*,rownum rn from(select a.*,b.username as musername,c.rtname as mrtname from ts_message a,ts_user b,ts_restaurant c where a.muserid=b.userid and a.mrtid=c.rtid order by mid)t)where rn between ? and ?";	
		TsMessage  pay=new TsMessage();
		List<TsMessage>  list=getEntities(sql,pay , (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	
	//通过id查询兑换记录
	public TsMessage findMessageById(Integer id){
		TsMessage message=new TsMessage();
		String sql="select a.*,b.username,c.rtname from ts_message a,ts_user b,ts_restaurant c where a.muserid=b.userid and a.mrtid=c.rtid and a.mid=?";
		TsMessage ts=getEntity(sql, message, id);
		return ts;
	}
	//添加兑换记录
	public Integer addMessage(Integer muserid,Integer mrtid,String mcontent,String mdate){
		String sql="insert into ts_message values(ts_message_seq.nextval,?,?,?,?)";
		Integer i=update(sql,muserid,mrtid,mcontent,mdate);
		return i;
	}
	//删除兑换记录
	public Integer deleteMessage(Integer id){
		String sql="delete from ts_message where mid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改兑换记录
	public Integer updateMessage(Integer muserid,Integer mrtid,String mcontent,String mdate,Integer mid){
		String sql="update ts_message set muserid=?,mrtid=?,mcontent=?,mdate=to_date('"+mdate+"','yyyy-MM-dd hh24:mi:ss') where mid=? ";
		Integer i=update(sql,muserid,mrtid,mcontent,mid);
		return i;
	}
	//查询所有兑换记录数量
	public Integer MessageCount(){
		String sql="select count(*) as cou from ts_message";
		Integer i=scalarNumber(sql);
		return i;
	}
}
