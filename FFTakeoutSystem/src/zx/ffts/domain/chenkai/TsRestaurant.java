package zx.ffts.domain.chenkai;

import java.io.Serializable;

public class TsRestaurant implements Serializable{
	private Integer rtid;			//编号
	private String rtname;			//店名
	private String rtaddr;			//地址
	private Integer rtowner;		//店主编号
	private String owner;			//店主名
	private String rtpic;			//图片
	private String rtcontent;		//公告
	private String rtdate;			//开店时间
	private String rtonbuz;			//营业时间
	private Integer rtstatus;		//营业状态
	public Integer getRtid() {
		return rtid;
	}
	public void setRtid(Integer rtid) {
		this.rtid = rtid;
	}
	public String getRtname() {
		return rtname;
	}
	public void setRtname(String rtname) {
		this.rtname = rtname;
	}
	public String getRtaddr() {
		return rtaddr;
	}
	public void setRtaddr(String rtaddr) {
		this.rtaddr = rtaddr;
	}
	public Integer getRtowner() {
		return rtowner;
	}
	public void setRtowner(Integer rtowner) {
		this.rtowner = rtowner;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getRtpic() {
		return rtpic;
	}
	public void setRtpic(String rtpic) {
		this.rtpic = rtpic;
	}
	public String getRtcontent() {
		return rtcontent;
	}
	public void setRtcontent(String rtcontent) {
		this.rtcontent = rtcontent;
	}
	public String getRtdate() {
		return rtdate;
	}
	public void setRtdate(String rtdate) {
		this.rtdate = rtdate;
	}
	public String getRtonbuz() {
		return rtonbuz;
	}
	public void setRtonbuz(String rtonbuz) {
		this.rtonbuz = rtonbuz;
	}
	public Integer getRtstatus() {
		return rtstatus;
	}
	public void setRtstatus(Integer rtstatus) {
		this.rtstatus = rtstatus;
	}
	public TsRestaurant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TsRestaurant(Integer rtid, String rtname, String rtaddr,
			Integer rtowner, String owner, String rtpic, String rtcontent,
			String rtdate, String rtonbuz, Integer rtstatus) {
		super();
		this.rtid = rtid;
		this.rtname = rtname;
		this.rtaddr = rtaddr;
		this.rtowner = rtowner;
		this.owner = owner;
		this.rtpic = rtpic;
		this.rtcontent = rtcontent;
		this.rtdate = rtdate;
		this.rtonbuz = rtonbuz;
		this.rtstatus = rtstatus;
	}

	
	
	
}
