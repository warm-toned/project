package zx.ffts.web.action.chenkai;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


import zx.ffts.domain.chenkai.TsGift;
import zx.ffts.domain.chenkai.TsGiftRecord;
import zx.ffts.domain.chenkai.TsMenu;
import zx.ffts.domain.chenkai.TsMenuMsg;
import zx.ffts.domain.chenkai.TsMessage;
import zx.ffts.domain.chenkai.TsOrder;
import zx.ffts.domain.chenkai.TsPay;
import zx.ffts.domain.chenkai.TsRestaurant;
import zx.ffts.domain.chenkai.TsUser;


import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	protected HttpServletRequest req;
	protected HttpSession ses;
	protected HttpServletResponse res;
	protected TsUser tsuser;
	protected List<TsUser> userlist;
	protected TsRestaurant tsrest;
	protected List<TsRestaurant> restlist;
	protected List<TsMessage> messlist;
	protected List<TsMenu> menulist;
	protected List<TsMenuMsg> menumsglist;
	protected List<TsOrder> orderlist;
	protected List<Map<String, Object>> olist;
	protected List<TsPay> paylist;
	protected List<TsGift> giftlist;
	protected List<TsGiftRecord> greclist;
	
	
	protected File photo;		// 用于填充上传的文件
	protected String photoFileName;		// 用于填充上传的文件名称
	
	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	
	
	public TsUser getTsuser() {
		return tsuser;
	}

	public void setTsuser(TsUser tsuser) {
		this.tsuser = tsuser;
	}

	
	
	public List<TsUser> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<TsUser> userlist) {
		this.userlist = userlist;
	}

	public List<TsRestaurant> getRestlist() {
		return restlist;
	}

	public void setRestlist(List<TsRestaurant> restlist) {
		this.restlist = restlist;
	}

	public List<TsMessage> getMesslist() {
		return messlist;
	}

	public void setMesslist(List<TsMessage> messlist) {
		this.messlist = messlist;
	}

	
	
	public List<TsMenu> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<TsMenu> menulist) {
		this.menulist = menulist;
	}

	
	public List<TsMenuMsg> getMenumsglist() {
		return menumsglist;
	}

	public void setMenumsglist(List<TsMenuMsg> menumsglist) {
		this.menumsglist = menumsglist;
	}

	
	
	public List<TsOrder> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<TsOrder> orderlist) {
		this.orderlist = orderlist;
	}
	

	public List<Map<String, Object>> getOlist() {
		return olist;
	}

	public void setOlist(List<Map<String, Object>> olist) {
		this.olist = olist;
	}

	

	public List<TsPay> getPaylist() {
		return paylist;
	}

	public void setPaylist(List<TsPay> paylist) {
		this.paylist = paylist;
	}

	
	public List<TsGift> getGiftlist() {
		return giftlist;
	}

	public void setGiftlist(List<TsGift> giftlist) {
		this.giftlist = giftlist;
	}

	
	
	public List<TsGiftRecord> getGreclist() {
		return greclist;
	}

	public void setGreclist(List<TsGiftRecord> greclist) {
		this.greclist = greclist;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		this.req=req;
		this.ses=req.getSession();
	}

	@Override
	public void setServletResponse(HttpServletResponse res) {
		// TODO Auto-generated method stub
		this.res=res;
	}

}
