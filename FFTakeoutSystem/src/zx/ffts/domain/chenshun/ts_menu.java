package zx.ffts.domain.chenshun;

import java.io.Serializable;

/**
 * 菜单表
 * @author Administrator
 *
 */
public class ts_menu implements Serializable{
	  private  Integer  muid;   //菜单id
	  private  Integer murtid;     //商店id
	  private String  muname ;    //菜的名称
	  private Integer  muprice;   //菜的价格
	  private String  mupic ;  //菜的图片
	  private String  mutype ;   //菜的问题
	  private String  mudesc ;   //描述
	  private Integer   musale;            //--销量
	  private Integer mustatus ; //--如果是1代表已售完
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
	public ts_menu(Integer muid, Integer murtid, String muname,
			Integer muprice, String mupic, String mutype, String mudesc,
			Integer musale, Integer mustatus) {
		super();
		this.muid = muid;
		this.murtid = murtid;
		this.muname = muname;
		this.muprice = muprice;
		this.mupic = mupic;
		this.mutype = mutype;
		this.mudesc = mudesc;
		this.musale = musale;
		this.mustatus = mustatus;
	}
	public ts_menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	  

}
