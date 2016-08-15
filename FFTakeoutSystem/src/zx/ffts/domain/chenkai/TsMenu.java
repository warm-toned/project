package zx.ffts.domain.chenkai;

public class TsMenu {
	private Integer muid;		//菜单编号
	private Integer murtid;  	//所属商店
	private String mrtname;		//所属商店名
	private String muname;		//菜单名称
	private Integer muprice;	//价格
	private String mupic;		//图片
	private String mutype;		//类型
	private String mudesc;		//描述
	private Integer musale;		//销量
	private Integer mustatus;	//状态，是否售完
	public Integer getMuid() {
		return muid;
	}
	public void setMuid(Integer muid) {
		this.muid = muid;
	}
	public Integer getMurtid() {
		return murtid;
	}
	public void setMurtid(Integer murtid) {
		this.murtid = murtid;
	}
	public String getMrtname() {
		return mrtname;
	}
	public void setMrtname(String mrtname) {
		this.mrtname = mrtname;
	}
	public String getMuname() {
		return muname;
	}
	public void setMuname(String muname) {
		this.muname = muname;
	}
	public Integer getMuprice() {
		return muprice;
	}
	public void setMuprice(Integer muprice) {
		this.muprice = muprice;
	}
	public String getMupic() {
		return mupic;
	}
	public void setMupic(String mupic) {
		this.mupic = mupic;
	}
	public String getMutype() {
		return mutype;
	}
	public void setMutype(String mutype) {
		this.mutype = mutype;
	}
	public String getMudesc() {
		return mudesc;
	}
	public void setMudesc(String mudesc) {
		this.mudesc = mudesc;
	}
	public Integer getMusale() {
		return musale;
	}
	public void setMusale(Integer musale) {
		this.musale = musale;
	}
	public Integer getMustatus() {
		return mustatus;
	}
	public void setMustatus(Integer mustatus) {
		this.mustatus = mustatus;
	}
	public TsMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TsMenu(Integer muid, Integer murtid, String mrtname, String muname,
			Integer muprice, String mupic, String mutype, String mudesc,
			Integer musale, Integer mustatus) {
		super();
		this.muid = muid;
		this.murtid = murtid;
		this.mrtname = mrtname;
		this.muname = muname;
		this.muprice = muprice;
		this.mupic = mupic;
		this.mutype = mutype;
		this.mudesc = mudesc;
		this.musale = musale;
		this.mustatus = mustatus;
	}
	@Override
	public String toString() {
		return "TsMenu [mrtname=" + mrtname + ", mudesc=" + mudesc + ", muid="
				+ muid + ", muname=" + muname + ", mupic=" + mupic
				+ ", muprice=" + muprice + ", murtid=" + murtid + ", musale="
				+ musale + ", mustatus=" + mustatus + ", mutype=" + mutype
				+ "]";
	}

	
	
}
