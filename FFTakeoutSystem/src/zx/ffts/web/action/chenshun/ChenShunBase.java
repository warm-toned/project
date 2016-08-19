package zx.ffts.web.action.chenshun;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import zx.ffts.domain.chenshun.ts_menu;
import zx.ffts.domain.chenshun.ts_order;
import zx.ffts.domain.chenshun.up;

public class ChenShunBase  implements ServletRequestAware,ServletResponseAware{
	
       protected ts_menu tm;  //菜单
       protected ts_order to;  //订单
       protected HttpServletRequest request;
       protected HttpServletResponse response;
       protected HttpSession session;
       protected up u;    //上传
	
    
	
	public ChenShunBase() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChenShunBase(ts_menu tm, ts_order to, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, up u) {
		super();
		this.tm = tm;
		this.to = to;
		this.request = request;
		this.response = response;
		this.session = session;
		this.u = u;
	}
	public ts_menu getTm() {
		return tm;
	}
	public void setTm(ts_menu tm) {
		this.tm = tm;
	}
	public ts_order getTo() {
		return to;
	}
	public void setTo(ts_order to) {
		this.to = to;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public up getU() {
		return u;
	}
	public void setU(up u) {
		this.u = u;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	    this.session=request.getSession();
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
	    this.response=response;	
	}
       
	
}
