package zx.ffts.web.action.pain;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletResponseAware;

import zx.ffts.dao.pain.SenderDao;

public class SenderAction implements ServletResponseAware {
	private HttpServletResponse response;
	private SenderDao senderDao = new SenderDao();
	private Integer page;
	private Integer page2;
	private Integer size;
	private Integer size2;

	public Integer getPage2() {
		return page2;
	}

	public void setPage2(Integer page2) {
		this.page2 = page2;
	}

	public Integer getSize2() {
		return size2;
	}

	public void setSize2(Integer size2) {
		this.size2 = size2;
	}

	private Integer userid;
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	public void getUsableOrders() throws IOException {
		if (page == null || page == 0) {
			page = 1;
		}
		if (size == null || size == 0) {
			size = 10;
		}
		String count = senderDao.getPageCounts();
		Integer count2 = senderDao.getSendCounts(userid);
		Integer number = senderDao.getSavedNumbers(userid);
		JSONArray arr = senderDao.getUsableOrders(page, size);
		JSONArray arr2 = senderDao.getSavedOrder(userid);
		JSONArray arr3 = senderDao.getSendedOrders(page2, size2, userid);
		response.getWriter().write(
				"{'count':" + count + ",'list':" + arr.toString()
						+ ",'number':" + number + ",'save':" + arr2.toString()
						+ ",'count2':" + count2 + ",'send':" + arr3.toString()
						+ "}");
	}

	public void saveOrder() throws IOException {
		Integer result = senderDao.saveOrder(uuid, userid);
		response.getWriter().write("" + result);
	}

	public void sendToDest() throws IOException {
		response.getWriter().write("" + senderDao.received(uuid));
	}
}
