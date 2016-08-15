package zx.ffts.dao.chenshun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import zx.ffts.dao.DataDao;
import zx.ffts.domain.User;
import zx.ffts.domain.chenshun.ts_menu;
import zx.ffts.domain.chenshun.ts_order;
import zx.ffts.domain.chenshun.ts_user;


public class ChenShunDao extends DataDao {
	/**
	 * 返回某一家商店所有的菜单信息(分页显示)
	 * 
	 * @param id
	 * 
	 * 
	 * @return
	 */
	public List<Map<String,Object>> MenuList(Integer id,Integer pageindex,Integer pagesize,String type,Integer price,String name,String sort,String order) {
		String sql = "select * from ( select h.*,rownum r from ts_menu h where rownum<=?  and murtid=?";
		if(type!=null && !type.equals("") && !type.equals("请选择类型")){
			  sql+=" and mutype='"+type+"'";
		}
		if(price!=null && price!=0){
			sql+=" and muprice>="+price;
		}
		if(name!=null && !name.equals("")){
			sql+="  and muname like '%"+name+"%'";
		}
		sql+="  ) k where k.r>?";
		 if(sort!=null&&!sort.equals("")){
         	 sql+="   order by  "+sort+"  "+order; 	 
          }else{
        	   sql+=" order by mustatus asc";
        	  
          }
		 
		 return getMapList(sql,pagesize*pageindex,id,(pageindex-1)*pagesize);

	}
	/**
	 * 当前菜单总数
	 * @return
	 */
	public int MenuNum(Integer id,String type,Integer price,String name){
		String sql="select count(*) from ts_menu where murtid=?";
		if(type!=null && !type.equals("")){
			  sql+=" and mutype="+type;
		}
		if(price!=null && price!=0){
			sql+=" and mupic>="+price;
		}
		if(name!=null && !name.equals("")){
			sql+="  and name like '%"+name+"%'";
		}
	
		return scalarNumber(sql,id);
		
	}
	/**
	 * 返回某家店的所有菜
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> GetMenuList(Integer id){
		String sql="select * from ts_menu where murtid=?";
		return getMapList(sql, id);	
	}

	
/**
 * 为某个商店添加一个菜
 * @param t
 * @return
 */
	public int AddMenu(ts_menu t) {
		String sql = "insert into  ts_menu values(ts_menu_seq.nextval,?,?,?,?,?,?,0,?)";
		return update(sql, t.getMurtid(), t.getMuname(), t.getMuprice(),t.getMupic(),t.getMutype(),t.getMudesc(),t.getMustatus());
    
	}
	/**
	 * 下架某道菜
	 * @param muid
	 * @param murtid
	 * @return
	 */
	public int DeleteMenu(Integer muid){
		String sql="update ts_menu  set mustatus=1  where muid=?";
		try {
			return update(sql, muid);
		} catch (Exception e) {
		    throw new RuntimeException();
		}
		
	}
	/**
	 * 为某个商店修改某道菜
	 * @param t
	 * @return
	 */
	public int UpdateMenu(ts_menu t){
		String sql = "update ts_menu set muname=?,muprice=?,mupic=?,mutype=?,mudesc=?,mustatus=? where muid=?";
		return update(sql,t.getMuname(), t.getMuprice(), t
				.getMupic(), t.getMutype(), t.getMudesc(),t.getMustatus(),t.getMuid());

	}
	
	
	/**
	 * 返回所有菜的品类
	 * @return
	 */
	public JSONArray MuType(){
		String sql="select mutype from ts_menu group by mutype";
		    JSONArray  json= getJsonArray(sql);
		     JSONObject object=new JSONObject();
		     object.put("MUTYPE","请选择类型");
		     json.add(0, object);
		return json;
	}
	
	
	/************************************************************对订单的操作************************************/
	/**
	 * 返回某家店的所有订单
	 * 
	 * @param id
	 * @return
	 */
	public List<Map<String, String>> OrderList(Integer id,Integer status,Integer pageindex,Integer pagesize,String sort,String order) {
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		String sql = "select * from(select username,ouuid,ortid,rtname,rtpic,odate,ostatus,row_number()over(order by odate desc) rm from ts_order o  inner join ts_restaurant r   on o.ortid=r.rtid   inner join ts_user u  on u.userid=o.ouserid  and ouuid is not null and ortid=?";
    
		if(status!=null && status!=-1)
           {
        	   sql+="   and ostatus>="+status;    	   
           }else{
        	   sql+="   and ostatus>="+2;    	 
        	   
           }
           sql+="  group by username,ouuid,ortid,rtname,rtpic,odate,ostatus) where rm between ? and ? ";
           if(sort!=null&&!sort.equals("")){
           	 sql+="   order by   "+sort+"  "+order; 	 
            }  
           for (Map<String, Object> map : getMapList(sql,id,(pageindex-1)*pagesize,pageindex*pagesize)){
			    Map<String,String> map2=new HashMap<String, String>();
			    map2.put("RM", map.get("RM").toString());
			    map2.put("USERNAME", map.get("USERNAME").toString());
			   // map2.put("MUNAME", map.get("MUNAME").toString());
			   // map2.put("MUPRICE", map.get("MUPRICE").toString());
			    map2.put("ORTID", map.get("ORTID").toString());
			    map2.put("ODATE", map.get("ODATE").toString());
			    map2.put("OSTATUS", map.get("OSTATUS").toString());
			  //  map2.put("OCOUNT", map.get("OCOUNT").toString());
			  //  map2.put("SUM", map.get("SUM").toString());
			    map2.put("OUUID", map.get("OUUID").toString());
			    list.add(map2);
			   
		}
        
           return list;
	}
	/**
	 * 加载某个店所有的订单啊
	 * @param id
	 * @return
	 */
	  public List<Map<String,Object>> GetListOrder(Integer id){
		  String sql="select  k.oid,u.username,m.muname,m.muprice,k.odate,k.ostatus,k.ocount,(m.muprice*k.ocount) as sum from ( select t.*,rownum r from ts_order t     where ortid=? ) k    inner join ts_menu m  on k.omuid=m.muid  inner join ts_user u  on u.userid=k.ouserid";
		  return getMapList(sql, id);
		  
	  }
	
	/**
	 * 修改订单
	 * @param t
	 * @return
	 */
	public int UpdateOrder(ts_order t){
		String sql="update ts_order set ocount=?,ostatus=? where ortid=? and ouserid? and omuid=?";
		return update(sql, t.getOcount(),t.getOstatus(),t.getOrtid(),t.getOuserid(),t.getOmuid());
		
}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int DeleteOrder(Integer id){
		String sql="delete from ts_order where ortid=?";
		return update(sql, id);
		
	}
	
	/**
	 * 当前订单总数
	 * @return
	 */
	public int OrderNum(Integer id,Integer status){
		String sql="select count(*) from ts_order where ortid=?";
		 if(status!=null && status!=-1){
      	   sql+="   and ostatus="+status;    	   		
         }	
		return scalarNumber(sql,id);
		
	}
	/**
	 * 柱状图形数据(菜单表)
	 * @return
	 * @throws Exception
	 */
	 public Map<String,Object> getList(Integer id) throws Exception{
		 Map<String,Object> map=new HashMap<String, Object>();
		 String sql="select * from ts_menu where murtid=?";
		 List<Map<String,Object>> list=getMapList(sql, id);
		 for (Map<String, Object> map2 : list) {
			  map.put(map2.get("MUNAME").toString(), map2.get("MUPRICE").toString());

		}
		 return map;
		 
	 }
	 /**
	  * 返回某家店所有还没有处理的订单
	  * @param id
	  * @return
	  */
	 
	 public List<Map<String,String>> OrderListNo(Integer id,Integer pageindex,Integer pagesize,String sort,String order){  //店的id
		 List<Map<String,String>>  list=new ArrayList<Map<String,String>>();
		 String sql="select * from(select username,ouuid,ortid,rtname,rtpic,odate,ostatus,row_number()over(order by odate desc) rm from ts_order o  inner join ts_restaurant r   on o.ortid=r.rtid   inner join ts_user u  on u.userid=o.ouserid  and ouuid is not null and ortid=? and ostatus=2    group by username,ouuid,ortid,rtname,rtpic,odate,ostatus) where rm between ? and ?";
		  if(sort!=null&&!sort.equals("")){
	           	 sql+=" order by   "+sort+"  "+order; 	 
	            }else{
	           	 sql+="   order by odate desc"; 	 
	            	
	            }
         for (Map<String, Object> map : getMapList(sql, id,pagesize*(pageindex-1)+1,pagesize*pageindex)) {
			   Map<String,String>  map2=new HashMap<String, String>();
			    map2.put("RM", map.get("RM").toString());
			   map2.put("USERNAME", map.get("USERNAME").toString());
			   // map2.put("MUNAME", map.get("MUNAME").toString());
			   // map2.put("MUPRICE", map.get("MUPRICE").toString());
			    map2.put("ORTID", map.get("ORTID").toString());
			     map2.put("ODATE", map.get("ODATE").toString());
			    map2.put("OSTATUS", map.get("OSTATUS").toString());
			   // map2.put("OCOUNT", map.get("OCOUNT").toString());
			   // map2.put("SUM", map.get("SUM").toString());
			    map2.put("OUUID", map.get("OUUID").toString());
			    list.add(map2);
		}
		 
		 return list;
	 }
	 
	 /**
	  * 没处理的总数
	  * @param id
	  * @return
	  */
		public int OrderNumNo(Integer id){
			String sql="    select count(*) from   (select ouuid from ts_order  where ortid=? and ouuid is not null group by ouuid)";
			
			return scalarNumber(sql,id);
			
		}
    /**
     * 返回某家店所有已经处理过的订单
     * @param id
     * @return
     */
	 public List<Map<String,String>> OrderListYes(Integer id,Integer pageindex,Integer pagesize,String sort,String order){
		 List<Map<String,String>>  list=new ArrayList<Map<String,String>>();
		 String sql =" select * from(select ouuid,ortid,rtname,rtpic,odate,ostatus,row_number()over(order by odate desc) rm from  ts_order o,ts_restaurant r where o.ortid=r.rtid  and ouuid is not null and ortid=?  and ostatus=3 group by ouuid,ortid,rtname,rtpic,odate,ostatus) where rm between ? and ? ";
		 if(sort!=null&&!sort.equals("")){
           	 sql+=" order by   "+sort+"  "+order; 	 
            }  
		 for (Map<String, Object> map : getMapList(sql, id,pagesize*(pageindex-1)+1,pageindex*pagesize)) {
			   Map<String,String>  map2=new HashMap<String, String>();
			    map2.put("RM", map.get("RM").toString());
				  //  map2.put("USERNAME", map.get("USERNAME").toString());
				   // map2.put("MUNAME", map.get("MUNAME").toString());
				   // map2.put("MUPRICE", map.get("MUPRICE").toString());
				    map2.put("ORTID", map.get("ORTID").toString());
				     map2.put("ODATE", map.get("ODATE").toString());
				    map2.put("OSTATUS", map.get("OSTATUS").toString());
				   // map2.put("OCOUNT", map.get("OCOUNT").toString());
				   // map2.put("SUM", map.get("SUM").toString());
				    map2.put("OUUID", map.get("OUUID").toString());
				    list.add(map2);
		}
		 
		 return list;
		 
	 }
	 /**
	  * 已处理订单的总数
	  * @param id
	  * @return
	  */
		public int OrderNumYes(Integer id){
			String sql="select count(*) from ts_order where ortid=? and ostatus =3";
			return scalarNumber(sql,id);
			
		}
		
		
		
		/**
		 * 单机接单的时候
		 * @param id
		 * @return
		 */
	public int JieDanOrder(Integer id,String ouuid){  //根据店的id和uuid
		String sql="update ts_order set ostatus=3 where  ortid=? and ouuid=?";
		return update(sql, id,ouuid);
			
	}
	
	/**
	 * 根据uuid查看订单
	 * @return
	 */
	public List<Map<String,Object>> OrderXiangQing(String ouuid){
		
		String sql="select realname,muname,ocount,muprice,(ocount*muprice) msum ,tel,address,rtname from ts_order o inner join ts_menu m on o.omuid=m.muid  inner join  ts_user t  on t.userid=o.ouserid    inner join  ts_restaurant tr  on tr.rtid=o.ortid where  ouuid=?";
		return getMapList(sql, ouuid);
        
	}
	
	
	/**
	 * 修改密码
	 * @param t
	 * @return
	 */
   public int UpdateUserPassword(User t){
	   String sql="update ts_user set pwd=? where userid=?";
	   return update(sql,t.getPwd(),t.getUserid());   
	   
   }
	
   /**
    * 根据用户id返回店的id
    * @param id
    * @return
    */
   public int UserId(Integer id){
	   String sql="select RTID from ts_restaurant  where rtowner=?";
	   return scalarNumber(sql,id);
   }

	 
	
	
	
	
	

}
