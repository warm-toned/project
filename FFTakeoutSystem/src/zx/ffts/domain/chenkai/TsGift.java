package zx.ffts.domain.chenkai;

import java.io.Serializable;

public class TsGift implements Serializable{
	private Integer gid;			//编号
	private String gname;			//礼品名称
	private String gpic;			//图片
	private Integer greqscore;		//所需积分
	private Integer gcount;			//已兑换数量
	private Integer gsum;			//总库存数
	private String gdesc;			//描述
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGpic() {
		return gpic;
	}
	public void setGpic(String gpic) {
		this.gpic = gpic;
	}
	public Integer getGreqscore() {
		return greqscore;
	}
	public void setGreqscore(Integer greqscore) {
		this.greqscore = greqscore;
	}
	public Integer getGcount() {
		return gcount;
	}
	public void setGcount(Integer gcount) {
		this.gcount = gcount;
	}
	public Integer getGsum() {
		return gsum;
	}
	public void setGsum(Integer gsum) {
		this.gsum = gsum;
	}
	public String getGdesc() {
		return gdesc;
	}
	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}
	public TsGift() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TsGift(Integer gid, String gname, String gpic, Integer greqscore,
			Integer gcount, Integer gsum, String gdesc) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.gpic = gpic;
		this.greqscore = greqscore;
		this.gcount = gcount;
		this.gsum = gsum;
		this.gdesc = gdesc;
	}
	@Override
	public String toString() {
		return "TsGift [gcount=" + gcount + ", gdesc=" + gdesc + ", gid=" + gid
				+ ", gname=" + gname + ", gpic=" + gpic + ", greqscore="
				+ greqscore + ", gsum=" + gsum + "]";
	}
	
}
