package zx.ffts.dao.chenkai;

import java.util.List;
import java.util.Map;

import zx.ffts.dao.DataDao;
import zx.ffts.domain.chenkai.TsRestaurant;



public class TsRestaurantDao extends DataDao {
	
	//查询所有店家
	public List<TsRestaurant>  getRestList(Integer nowPage,Integer pageSize){
		TsRestaurant rest=new TsRestaurant();
		String sql="select * from (select t.*,rownum rn from(select rtid,rtname,rtaddr,rtowner,(select username from ts_user where ts_user.userid=ts_restaurant.rtowner )as owner,rtpic,rtcontent,rtdate,rtonbuz,rtstatus from ts_restaurant order by rtid)t)where rn between ? and ?";	
		List<TsRestaurant> list=getEntities(sql,rest,(((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	//查询所有店家
	public List<TsRestaurant>  getAllRest(){
		String sql="select * from ts_restaurant order by rtid";	
		TsRestaurant  ts=new TsRestaurant();
		List<TsRestaurant>  list=getEntities(sql,ts);
		return list;
	}
	
	//下载所有商家信息
	public List<Map<String, Object>> WriteRest(){
		String sql="select rtid,rtname,rtaddr,rtowner,(select username from ts_user where ts_user.userid=ts_restaurant.rtowner )as owner,rtpic,rtcontent,rtdate,rtonbuz,rtstatus from ts_restaurant order by rtid";
		List<Map<String, Object>> list=getMapList(sql);
		return list;
	}
	
	//通过id查询店家
	public TsRestaurant findRestById(Integer id){
		TsRestaurant rest=new TsRestaurant();
		String sql="select rtid,rtname,rtaddr,rtowner,(select username from ts_user where ts_user.userid=ts_restaurant.rtowner )as owner,rtpic,rtcontent,rtdate,rtonbuz,rtstatus from ts_restaurant where rtid=?";
		TsRestaurant ts=getEntity(sql, rest, id);
		return ts;
	}
	//添加店家
	public Integer addRest(String rtname,String rtaddr,Integer rtowner,String rtpic,String rtcontent,String rtonbuz,String rtstatus){
		if (rtpic==null) {
			String sql="insert into ts_restaurant values(ts_restaurant_seq.nextval,?,?,?,default,?,sysdate,?,?)";
			Integer i=update(sql,rtname,rtaddr,rtowner,rtcontent,rtonbuz,rtstatus);
			return i;
		}else{
		String sql="insert into ts_restaurant values(ts_restaurant_seq.nextval,?,?,?,?,?,sysdate,?,?)";
		Integer i=update(sql,rtname,rtaddr,rtowner,rtpic,rtcontent,rtonbuz,rtstatus);
		return i;
		}
	}
	//删除店家
	public Integer deleteRest(Integer id){
		String sql="delete from ts_restaurant where rtid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改店家
	public Integer updateRest(String rtname,String rtaddr,String rtowner,String rtpic,String rtcontent,String rtdate,String rtonbuz,Integer rtstatus,Integer rtid){
		String sql="update ts_restaurant set rtname=?,rtaddr=?,rtowner=?,rtpic=?,rtcontent=?,rtdate=to_date('"+rtdate+"','yyyy-MM-dd hh24:mi:ss'),rtonbuz=?,rtstatus=? where rtid=? ";
		Integer i=update(sql, rtname,rtaddr,rtowner,rtpic,rtcontent,rtonbuz,rtstatus,rtid);
		return i;
	}
	//查询所有店家数量
	public Integer restCount(){
		String sql="select count(*) as cou from ts_restaurant";
		Integer i=scalarNumber(sql);
		return i;
	}
}
