package zx.ffts.dao.chenkai;

import java.util.List;
import java.util.Map;

import zx.ffts.dao.DataDao;
import zx.ffts.domain.chenkai.TsGift;


public class TsGiftDao extends DataDao {
	
	//查询所有礼品
	public List<TsGift>  getGiftList(Integer nowPage,Integer pageSize){
		String sql="select * from (select t.*,rownum rn from(select * from ts_gift order by gid)t)where rn between ? and ?";	
		TsGift  gift=new TsGift();
		List<TsGift>  list=getEntities(sql,gift , (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	//查询所有礼品
	public List<TsGift>  getAllGift(){
		String sql="select * from ts_gift order by gid";	
		TsGift  gift=new TsGift();
		List<TsGift>  list=getEntities(sql,gift );
		return list;
	}
	
	//通过id查询礼品
	public TsGift findGiftById(Integer id){
		TsGift gift=new TsGift();
		String sql="select * from ts_gift where gid=?";
		TsGift ts=getEntity(sql, gift, id);
		return ts;
	}
	//添加礼品
	public Integer addGift (String gname,String gpic,Integer greqscore,Integer gsum,String gdesc){
		if (gpic==null) {
			String sql="insert into ts_gift values(ts_gift_seq.nextval,?,'image/defaults/giftdefault.jpg',?,0,?,?)";
			Integer i=update(sql,gname,greqscore,gsum,gdesc);
			return i;
		}else{
			String sql="insert into ts_gift values(ts_gift_seq.nextval,?,?,?,0,?,?)";
			Integer i=update(sql,gname,gpic,greqscore,gsum,gdesc);
			return i;
		}
	}
	//删除礼品
	public Integer deleteGift(Integer id){
		String sql="delete from ts_gift where gid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改礼品
	public Integer updateGift(String gname,String gpic,Integer greqscore,Integer gcount,Integer gsum,String gdesc,Integer gid){
		String sql="update ts_gift set gname=?,gpic=?,greqscore=?,gcount=?,gsum=?,gdesc=? where gid=? ";
		Integer i=update(sql,gname,gpic,greqscore,gcount,gsum,gdesc,gid);
		return i;
	}
	//查询所有礼品数量
	public Integer giftCount(){
		String sql="select count(*) as cou from ts_gift";
		Integer i=scalarNumber(sql);
		return i;
	}
	
	//下载所有礼品
	public List<Map<String, Object>> WriteGift(){
		String sql="select * from ts_gift";
		List<Map<String, Object>> list=getMapList(sql);
		return list;
	}
	
}
