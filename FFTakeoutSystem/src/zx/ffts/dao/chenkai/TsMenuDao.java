package zx.ffts.dao.chenkai;

import java.util.List;
import java.util.Map;

import zx.ffts.dao.DataDao;
import zx.ffts.domain.chenkai.TsMenu;



public class TsMenuDao extends DataDao {
	
	//查询所有菜单
	public List<TsMenu>  getMenuList(Integer nowPage,Integer pageSize){
		String sql="select * from (select t.*,rownum rn from(select a.*,b.rtname as mrtname from ts_menu a,ts_restaurant b where a.murtid=b.rtid order by muid )t)where rn between ? and ?";	
		TsMenu  menu=new TsMenu();
		List<TsMenu>  list=getEntities(sql,menu , (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	//下载所有菜单
	public List<Map<String, Object>> WriteMenu(){
		String sql="select a.*,b.rtname as mrtname from ts_menu a,ts_restaurant b where a.murtid=b.rtid order by a.murtid ";
			List<Map<String, Object>> list =getMapList(sql);
			return list;
	}
	
	//查询所有菜单
	public List<TsMenu>  getAllMenuByRest(Integer murtid){
		String sql="select * from ts_menu where murtid=?";	
		TsMenu  ts=new TsMenu();
		List<TsMenu>  list=getEntities(sql,ts,murtid);
		return list;
	}
	
	//查询所有菜单
	public List<TsMenu>  getAllMenu(){
		String sql="select * from ts_menu ";	
		TsMenu  ts=new TsMenu();
		List<TsMenu>  list=getEntities(sql,ts);
		return list;
	}
	
	//通过id查询兑换记录
	public TsMenu findMenuById(Integer id){
		TsMenu pay=new TsMenu();
		String sql="select a.*,b.rtname from ts_menu a,ts_restaurant b where a.murtid=b.rtid and a.muid=?";
		TsMenu ts=getEntity(sql, pay, id);
		return ts;
	}
	//添加兑换记录
	public Integer addMenu(Integer murtid,String muname,double muprice,String mupic,String mutype,String mudesc,Integer musale,Integer mustatus){
		String sql="insert into ts_menu values(ts_menu_seq.nextval,?,?,?,?,?,?,?,?)";
		Integer i=update(sql,murtid,muname,muprice,mupic,mutype,mudesc,musale,mustatus);
		return i;
	}
	//删除兑换记录
	public void deleteMenu(Integer id){
		String sql="delete from ts_menu where muid=?";
		update(sql, id);
		
	}
	//修改兑换记录
	public Integer updateMenu(Integer murtid,String muname,double muprice,String mupic,String mutype,String mudesc,Integer musale,Integer mustatus,Integer muid){
		String sql="update ts_menu set murtid=?,muname=?,muprice=?,mupic=?,mutype=?,mudesc=?,musale=?,mustatus=? where muid=? ";
		Integer i=update(sql,murtid,muname,muprice,mupic,mutype,mudesc,musale,mustatus,muid);
		return i;
	}
	//查询所有兑换记录数量
	public Integer menuCount(){
		String sql="select count(*) as cou from ts_menu";
		Integer i=scalarNumber(sql);
		return i;
	}
}
