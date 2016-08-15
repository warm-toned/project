package zx.ffts.web.action.xiongli;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import zx.ffts.dao.xiong.OrderDao;
import zx.ffts.dao.xiong.OrderFunctionDao;
import zx.ffts.dao.yyq.ts_restaurant_dao;

public class OrderAction implements ServletResponseAware {

	private HttpServletResponse response;
	private Integer userid;
	private Integer rtid;
	private Integer muid;
	private OrderDao orderDao = new OrderDao();
	private OrderFunctionDao functionDao = new OrderFunctionDao();
	private List<Map<String, Object>> cartinfos;
	private Map<String, Object> shopinfos;
	private Double ti;
	private String uuid;

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setTi(Double ti) {
		this.ti = ti;
	}

	public List<Map<String, Object>> getCartinfos() {
		return cartinfos;
	}

	public Map<String, Object> getShopinfos() {
		return shopinfos;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getRtid() {
		return rtid;
	}

	public void setRtid(Integer rtid) {
		this.rtid = rtid;
	}

	public Integer getMuid() {
		return muid;
	}

	public void setMuid(Integer muid) {
		this.muid = muid;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void addOrder() throws IOException {
		int line = orderDao.addOrder(userid, muid, rtid);
		if (line > 0) {
			line = functionDao.orderCount(userid, rtid, muid);
			response.getWriter().write("" + line);
		} else {
			response.getWriter().write("fail");
		}
	}

	public void minusOrder() throws IOException {
		int line = orderDao.minusOrder(userid, muid, rtid);
		if (line > 0) {
			line = functionDao.orderCount(userid, rtid, muid);
			response.getWriter().write("" + line);
		} else {
			response.getWriter().write("fail");
		}
	}

	public void clearCart() throws IOException {
		int line = orderDao.clearCart(userid, rtid);
		if (line > 0) {
			response.getWriter().write("success");
		} else {
			response.getWriter().write("fail");
		}
	}

	public void getCart() throws IOException {
		response.getWriter()
				.write(functionDao.getCart(userid, rtid).toString());
	}

	public String orderNow() {
		// 加载订单
		cartinfos = functionDao.getCartList(userid, rtid);
		shopinfos = new ts_restaurant_dao().getshopInfo(rtid);
		return "orderConfirm";
	}

	public String setOrder(){
		uuid = functionDao.setOrder(userid, rtid);
		return "orderPay";
	}
	
	public String payPage() throws ParseException {
		shopinfos = new ts_restaurant_dao().getshopInfo(rtid);
		Double money = functionDao.getOrderMoney(uuid);
		String timeStr = functionDao.getDeadline(ti,uuid);
		if(timeStr!=null){
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr);
			shopinfos.put("deadline", date.getTime());
		}
		shopinfos.put("money", money);
		return "payPage";
	}
	
	public void getDeadline(){
		functionDao.getDeadline(ti,uuid);
	}
}
