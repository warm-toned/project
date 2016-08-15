package zx.ffts.dao.chenkai;

import java.util.List;

import zx.ffts.dao.DataDao;
import zx.ffts.domain.chenkai.TsMenuMsg;


public class TsMenumsgDao extends DataDao {
	
	//查询所有菜单评论
	public List<TsMenuMsg>  getMenuMsgList(Integer nowPage,Integer pageSize){
		String sql="select * from (select t.*,rownum rn from(select a.*,b.username as mmusername,c.muname as mmmuname from ts_menumsg a,ts_user b,ts_menu c where a.mmuserid=b.userid and a.mmmuid=c.muid order by mmid)t)where rn between ? and ?";	
		TsMenuMsg  pay=new TsMenuMsg();
		List<TsMenuMsg>  list=getEntities(sql,pay , (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	
	//通过id查询菜单评论
	public TsMenuMsg findMenuMsgById(Integer id){
		TsMenuMsg pay=new TsMenuMsg();
		String sql="select a.*,b.username as mmusername,c.muname as mmmuname from ts_menumsg a,ts_user b,ts_menu c where a.mmuserid=b.userid and a.mmmuid=c.muid and a.mmid=?";
		TsMenuMsg ts=getEntity(sql, pay, id);
		return ts;
	}
	//添加菜单评论
	public Integer addMenuMsg(Integer mmuserid,Integer mmmuid,String mmcontent,Integer mmscore,String mmdate){
		String sql="insert into ts_menumsg values(ts_menumsg_seq.nextval,?,?,?,?,?)";
		Integer i=update(sql,mmuserid,mmmuid,mmcontent,mmscore,mmdate);
		return i;
	}
	//删除菜单评论
	public Integer deleteMenuMsg(Integer id){
		String sql="delete from ts_menumsg where mmid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改菜单评论
	public Integer updateMenuMsg(Integer mmuserid,Integer mmmuid,String mmcontent,Integer mmscore,String mmdate,Integer mmid){
		String sql="update ts_menumsg set mmuserid=?,mmmuid=?,mmcontent=?,mmscore=?,mmdate=to_date('"+mmdate+"','yyyy-MM-dd hh24:mi:ss') where mmid=? ";
		Integer i=update(sql,mmuserid,mmmuid,mmcontent,mmscore,mmid);
		return i;
	}
	//查询所有菜单评论数量
	public Integer MenuMsgCount(){
		String sql="select count(*) as cou from ts_menumsg";
		Integer i=scalarNumber(sql);
		return i;
	}
}
