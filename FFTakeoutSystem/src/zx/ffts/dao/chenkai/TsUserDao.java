package zx.ffts.dao.chenkai;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import zx.ffts.dao.DataDao;
import zx.ffts.domain.User;
import zx.ffts.domain.chenkai.TsUser;


public class TsUserDao extends DataDao {
	
	
	//登录
	public TsUser login(String name){
		TsUser user=new TsUser();
		String sql="select * from ts_user where username=?";
		TsUser ts=getEntity(sql, user, name);
		return ts;
	}
	//分页查询用户
	public List<TsUser>  getUserList(Integer nowPage,Integer pageSize ){
		String sql="select * from (select t.*,rownum rn from(select * from ts_user  order by userid)t)where rn between ? and ?";	
		TsUser  user=new TsUser();
		List<TsUser>  list=getEntities(sql,user , (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	//查询所有用户
	public List<TsUser>  getAllUser(){
		String sql="select * from ts_user order by userid";	
		TsUser  user=new TsUser();
		List<TsUser>  list=getEntities(sql,user);
		return list;
	}
	
	//查询所有店家
	public List<TsUser> GetAllOwner(){
		String sql="select * from ts_user where authority =3 order by userid";	
		TsUser  user=new TsUser();
		List<TsUser>  list=getEntities(sql,user);
		return list;
	}
	
	//查询所有配送员
	public List<TsUser> GetAllSender(){
		String sql="select * from ts_user where authority =2 order by userid";	
		TsUser  user=new TsUser();
		List<TsUser>  list=getEntities(sql,user);
		return list;
	}
	
	//下载所有用户
	public List<Map<String, Object>> WriteUser(){
		String sql="select * from ts_user order by userid";
		List<Map<String, Object>> list=getMapList(sql);
		return list;
	}
	
	//通过id查询用户
	public TsUser findUserById(Integer id){
		TsUser user=new TsUser();
		String sql="select * from ts_user where userid=?";
		TsUser ts=getEntity(sql, user, id);
		return ts;
	}
	//添加用户
	public Integer addUser(String username,String pwd,String tel,String email,String address,String realname,double balance,String gender,Integer authority,String photo){
		if (photo==null) {
			String sql="insert into ts_user values(ts_user_seq.nextval,?,?,?,?,?,?,?,0,?,sysdate,?,default)";
			Integer i=update(sql, username,pwd,tel,email,address,realname,balance,gender,authority);
			return i;
		}else{
			String sql="insert into ts_user values(ts_user_seq.nextval,?,?,?,?,?,?,?,0,?,sysdate,?,?)";
			Integer i=update(sql, username,pwd,tel,email,address,realname,balance,gender,authority,photo);
			return i;
		}
	}
	
	//删除用户
	public Integer deleteUser(Integer id){
		String sql="delete from ts_user where userid=?";
		Integer i=update(sql, id);
		return i;
	}
	
	//修改用户
	public Integer updateUser(String username,String pwd,String tel,String email,String address,String realname,double balance,Integer score,String gender,String regdate,Integer authority,String photo,Integer userid){
		String sql="update ts_user set username=?,pwd=?,tel=?,email=?,address=?,realname=?,balance=?,score=?,gender=?,regdate=to_date('"+regdate+"','yyyy-MM-dd hh24:mi:ss') ,authority=?,photo=? where userid=? ";
		Integer i=update(sql, username,pwd,tel,email,address,realname,balance,score,gender,authority,photo,userid);
		return i;
	}
	
	//修改用户为店主权限
	public Integer updateAOwner(Integer userid){
		String sql="update ts_user set authority=3 where userid=? ";
		Integer i=update(sql,userid);
		return i;
	}
	
	//修改用户为普通用户权限
	public Integer updateAUser(Integer userid){
		String sql="update ts_user set authority=1 where userid=? ";
		Integer i=update(sql,userid);
		return i;
	}
	
	//查询所有用户数量
	public Integer userCount(){
		String sql="select count(*) as cou from ts_user";
		Integer i=scalarNumber(sql);
		return i;
	}

	
}
