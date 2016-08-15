package zx.ffts.domain.chenkai;

import java.io.Serializable;

public class TsMessage implements Serializable{
	private Integer mid;			//编号
	private Integer muserid;		//评论人编号
	private String musername;		//评论人姓名
	private Integer mrtid;			//店铺编号
	private String mrtname;			//店铺名称
	private String mcontent;		//内容
	private String mdate;			//评论时间
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getMuserid() {
		return muserid;
	}
	public void setMuserid(Integer muserid) {
		this.muserid = muserid;
	}
	public String getMusername() {
		return musername;
	}
	public void setMusername(String musername) {
		this.musername = musername;
	}
	public Integer getMrtid() {
		return mrtid;
	}
	public void setMrtid(Integer mrtid) {
		this.mrtid = mrtid;
	}
	public String getMrtname() {
		return mrtname;
	}
	public void setMrtname(String mrtname) {
		this.mrtname = mrtname;
	}
	public String getMcontent() {
		return mcontent;
	}
	public void setMcontent(String mcontent) {
		this.mcontent = mcontent;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public TsMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TsMessage(Integer mid, Integer muserid, String musername,
			Integer mrtid, String mrtname, String mcontent, String mdate) {
		super();
		this.mid = mid;
		this.muserid = muserid;
		this.musername = musername;
		this.mrtid = mrtid;
		this.mrtname = mrtname;
		this.mcontent = mcontent;
		this.mdate = mdate;
	}
	@Override
	public String toString() {
		return "TsMessage [mcontent=" + mcontent + ", mdate=" + mdate
				+ ", mid=" + mid + ", mrtid=" + mrtid + ", mrtname=" + mrtname
				+ ", muserid=" + muserid + ", musername=" + musername + "]";
	}
	
	
}
