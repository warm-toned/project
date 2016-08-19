package zx.ffts.dao.yyq;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import zx.ffts.dao.DataDao;

public class GiftDao extends DataDao{
	//积分商品信息清单
		public List<Map<String,Object>> gift(){
			String sql="select * from ts_gift";
		    return getMapList(sql);
		}	
		@Test
		public void pub() {
			gift();
		}
		//根据用户id查询用户的积分
		
		public int UserScore(Integer uid){
			String sql="select score from ts_user where userid=?";	
			return scalarNumber(sql,uid);
			
		}
		//根据商品id查商品的积分
		public int GiftScore(Integer gid){
			String sql="select greqscore from ts_gift where gid=?";	
			return scalarNumber(sql,gid);
			
		}
		//修改用户的积分
		public int upUserScore(Integer score,Integer uid) {
			String sql="update ts_user set score=score-? where userid=?";
			
			return update(sql,score,uid);
			
		}
		//修改礼品信息的个数
		public int upGiftcount(Integer gid) {
			String sql="update ts_gift set gcount=gcount+1 where gid=?";
			return update(sql,gid);
		}
		//查询礼品的以兑换的个数
		public int gcount(Integer gid){
			String sql="select gcount from ts_gift where gid=?";
			return scalarNumber(sql,gid);
		}
		//插入兑换记录
		public int insChange(Integer gid,Integer uid) {
			String sql="insert into ts_giftrecord values (ts_giftrecord_seq.nextval,?,?,1,sysdate,0)";
			return update(sql,gid,uid);
		}
		
		//兑换记录
		public List<Map<String,Object>> giftrecord(Integer uid){
			String sql="select r.*,to_char(r.grdate,'yyyy-mm-dd hh24:mi:ss') gdate,g.greqscore,g.gname,g.gpic,u.username from ts_giftrecord r,ts_gift g,ts_user u where r.grgid=g.gid and r.gruserid=u.userid and u.userid=? order by r.grdate desc";
		    return getMapList(sql,uid);
		}	

}
