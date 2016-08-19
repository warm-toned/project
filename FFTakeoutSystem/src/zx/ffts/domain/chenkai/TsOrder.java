package zx.ffts.domain.chenkai;

import java.io.Serializable;

public class TsOrder implements Serializable{
	private Integer oid;			//订单编号
	private Integer ouserid;		//用户id
	private String ousername;		//用户名
	private Integer omuid;			//某个菜
	private String omuname;			//菜名
	private Integer ortid;			//店铺id
	private Integer ortname;		//店铺名
	private Integer ocount;			//数量
	private Integer osender;		//配送员id
	private String osendername;		//配送员姓名
	private String odate;			//配送时间
	private String ouuid;			
	private Integer ostatus;		//订单状态
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getOuserid() {
		return ouserid;
	}
	public void setOuserid(Integer ouserid) {
		this.ouserid = ouserid;
	}
	public String getOusername() {
		return ousername;
	}
	public void setOusername(String ousername) {
		this.ousername = ousername;
	}
	public Integer getOmuid() {
		return omuid;
	}
	public void setOmuid(Integer omuid) {
		this.omuid = omuid;
	}
	public String getOmuname() {
		return omuname;
	}
	public void setOmuname(String omuname) {
		this.omuname = omuname;
	}
	public Integer getOrtid() {
		return ortid;
	}
	public void setOrtid(Integer ortid) {
		this.ortid = ortid;
	}
	public Integer getOrtname() {
		return ortname;
	}
	public void setOrtname(Integer ortname) {
		this.ortname = ortname;
	}
	public Integer getOcount() {
		return ocount;
	}
	public void setOcount(Integer ocount) {
		this.ocount = ocount;
	}
	public Integer getOsender() {
		return osender;
	}
	public void setOsender(Integer osender) {
		this.osender = osender;
	}
	public String getOsendername() {
		return osendername;
	}
	public void setOsendername(String osendername) {
		this.osendername = osendername;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public String getOuuid() {
		return ouuid;
	}
	public void setOuuid(String ouuid) {
		this.ouuid = ouuid;
	}
	public Integer getOstatus() {
		return ostatus;
	}
	public void setOstatus(Integer ostatus) {
		this.ostatus = ostatus;
	}
	public TsOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TsOrder(Integer oid, Integer ouserid, String ousername,
			Integer omuid, String omuname, Integer ortid, Integer ortname,
			Integer ocount, Integer osender, String osendername, String odate,
			String ouuid, Integer ostatus) {
		super();
		this.oid = oid;
		this.ouserid = ouserid;
		this.ousername = ousername;
		this.omuid = omuid;
		this.omuname = omuname;
		this.ortid = ortid;
		this.ortname = ortname;
		this.ocount = ocount;
		this.osender = osender;
		this.osendername = osendername;
		this.odate = odate;
		this.ouuid = ouuid;
		this.ostatus = ostatus;
	}
	@Override
	public String toString() {
		return "TsOrder [ocount=" + ocount + ", odate=" + odate + ", oid="
				+ oid + ", omuid=" + omuid + ", omuname=" + omuname
				+ ", ortid=" + ortid + ", ortname=" + ortname + ", osender="
				+ osender + ", osendername=" + osendername + ", ostatus="
				+ ostatus + ", ouserid=" + ouserid + ", ousername=" + ousername
				+ ", ouuid=" + ouuid + "]";
	}
	
	
}
