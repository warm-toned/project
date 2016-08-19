package zx.ffts.dao.chenkai;

import java.util.List;
import java.util.Map;

import zx.ffts.dao.DataDao;
import zx.ffts.domain.chenkai.TsGiftRecord;


public class TsGiftRecordDao extends DataDao {
	
	//查询所有兑换记录
	public List<TsGiftRecord>  getTsGiftRecordList(Integer nowPage,Integer pageSize){
		String sql="select * from (select t.*,rownum rn from(select ts_giftrecord.*,(select gname from ts_gift where ts_gift.gid=ts_giftrecord.grgid) as grgname,(select username from ts_user where ts_user.userid=ts_giftrecord.gruserid) as grusername from ts_giftrecord order by grid)t)where rn between ? and ?";	
		TsGiftRecord  pay=new TsGiftRecord();
		List<TsGiftRecord>  list=getEntities(sql,pay , (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	
	//通过id查询兑换记录
	public TsGiftRecord findGiftRecordById(Integer id){
		TsGiftRecord pay=new TsGiftRecord();
		String sql="select * from ts_giftrecord where grid=?";
		TsGiftRecord ts=getEntity(sql, pay, id);
		return ts;
	}
	//添加兑换记录
	public Integer addGiftRecord(Integer grid,Integer gruserid,Integer grnum,String grdate,Integer grstatus){
		String sql="insert into ts_giftrecord values(ts_giftrecord_seq.nextval,?,?,?,?,?)";
		Integer i=update(sql,grid,gruserid,grnum,grdate,grstatus);
		return i;
	}
	//删除兑换记录
	public Integer deleteGiftRecord(Integer id){
		String sql="delete from ts_giftrecord where grid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改兑换记录
	public Integer updateGiftRecord(Integer grgid,Integer gruserid,Integer grnum,String grdate,Integer grstatus,Integer grid){
		String sql="update ts_giftrecord set grgid=?,gruserid=?,grnum=?,grdate=to_date('"+grdate+"','yyyy-MM-dd hh24:mi:ss'),grstatus=? where grid=? ";
		Integer i=update(sql,grgid,gruserid,grnum,grstatus,grid);
		return i;
	}
	//查询所有兑换记录数量
	public Integer GiftRecordCount(){
		String sql="select count(*) as cou from ts_giftrecord";
		Integer i=scalarNumber(sql);
		return i;
	}
	
	//下载礼品兑换记录表
	public List<Map<String, Object>> WriteGiftRec(){
		String sql="select ts_giftrecord.*,(select gname from ts_gift where ts_gift.gid=ts_giftrecord.grgid) as grgname,(select username from ts_user where ts_user.userid=ts_giftrecord.gruserid) as grusername from ts_giftrecord";
		List<Map<String, Object>> list=getMapList(sql);
		return list;
	}
}
