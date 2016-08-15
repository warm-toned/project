package zx.ffts.web.action.xiongli;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import zx.ffts.dao.xiong.OrderDao;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	protected HttpServletRequest request;
	protected HttpSession session;
	protected HttpServletResponse response;
	protected OrderDao odao=new OrderDao();
	
	protected String uuid;
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
