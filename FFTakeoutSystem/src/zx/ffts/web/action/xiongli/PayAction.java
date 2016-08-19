package zx.ffts.web.action.xiongli;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import zx.ffts.dao.transaction.PayNowTransaction;
import zx.ffts.dao.xiong.OrderFunctionDao;

public class PayAction implements ServletResponseAware {

	private Integer pay;
	private String uuid;
	private Double money;

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	private HttpServletResponse response;
	private OrderFunctionDao functionDao = new OrderFunctionDao();

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 支付
	 * 
	 * @throws IOException
	 */
	public void payNow() throws IOException {
		String result = functionDao.doTransaction(new PayNowTransaction(),
				uuid, money).toString();
		response.getWriter().write(result);
	}

	/**
	 * 加载信息
	 * 
	 * @throws IOException
	 */
	public void loadOrderInfo() throws IOException {
		response.getWriter().write(functionDao.getOrderInfo(uuid).toString());
	}
}
