package zx.ffts.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class User implements Serializable{

	private Integer userid;
	private String username;
	private String pwd;
	private String tel;
	private String email;
	private String address;
	private String realname;
	private Double balance;
	private Integer score;
	private String gender;
	private Date regdate;
	private Integer authority;
	private String photo;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", pwd="
				+ pwd + ", tel=" + tel + ", email=" + email + ", address="
				+ address + ", realname=" + realname + ", balance=" + balance
				+ ", score=" + score + ", gender=" + gender + ", regdate="
				+ regdate + ", authority=" + authority + ", photo=" + photo
				+ "]";
	}

	public User() {
		super();
	}

	public User(Integer userid, String username, String pwd, String tel,
			String email, String address, String realname, Double balance,
			Integer score, String gender, Date regdate, Integer authority,
			String photo) {
		super();
		this.userid = userid;
		this.username = username;
		this.pwd = pwd;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.realname = realname;
		this.balance = balance;
		this.score = score;
		this.gender = gender;
		this.regdate = regdate;
		this.authority = authority;
		this.photo = photo;
	}
	
}
