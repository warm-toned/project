package zx.ffts.dao.pain;

import org.junit.Test;

import zx.ffts.dao.DataAccessObject;
import zx.ffts.domain.User;

public class UserDao extends DataAccessObject {

	@Test
	public void test() {
		System.out.println(login("root", "1111"));
	}

	public User login(String username, String pwd) {
		String sql = "select * from ts_user where username=? and pwd=?";
		return getEntity(sql, new User(), username, pwd);
	}

	public Integer uniqueUsername(String username) {
		String sql = "select count(*) from ts_user where username=?";
		return executeScalarInteger(sql, username);
	}

	public Integer getOrderNumber(Integer userid) {
		String sql = "select count(*) from (select ouuid from ts_order where ouserid=? group by ouuid)";
		return executeScalarInteger(sql, userid);
	}

	public Integer editGender(String gender, Integer userid) {
		String sql = "update ts_user set gender=? where userid=?";
		return executeUpdate(sql, gender, userid);
	}

	public Integer editTel(String tel, Integer userid) {
		String sql = "update ts_user set tel=? where userid=?";
		return executeUpdate(sql, tel, userid);
	}

	public Integer editAddress(String address, Integer userid) {
		String sql = "update ts_user set address=? where userid=?";
		return executeUpdate(sql, address, userid);
	}

	public Integer editRealname(String realname, Integer userid) {
		String sql = "update ts_user set realname=? where userid=?";
		return executeUpdate(sql, realname, userid);
	}

	public Integer editEmail(String email, Integer userid) {
		String sql = "update ts_user set email=? where userid=?";
		return executeUpdate(sql, email, userid);
	}

	public Integer editPhoto(String photoPath, Integer userid) {
		String sql = "update ts_user set photo=? where userid=?";
		return executeUpdate(sql, photoPath, userid);
	}

	public Integer regist(String uname, String pwd, String tel, String address,
			String realname, String gender, String photo) {
		String sql = "select ts_user_seq.nextval from dual";
		Integer id = executeScalarInteger(sql);
		sql = "insert into ts_user values(?,?,?,?,null,?,?,20000,200000,?,sysdate,1,?)";
		Integer line = executeUpdate(sql, id, uname, pwd, tel, address,
				realname, gender, photo);
		return line > 0 ? id : -1;
	}

	public User getUserById(Integer id) {
		String sql = "select * from ts_user where userid=?";
		return getEntity(sql, new User(), id);
	}
}
