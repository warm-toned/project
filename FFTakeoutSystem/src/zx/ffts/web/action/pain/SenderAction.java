package zx.ffts.web.action.pain;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import zx.ffts.dao.pain.SenderDao;

public class SenderAction implements ServletResponseAware {
	private HttpServletResponse response;
	private SenderDao senderDao = new SenderDao();
	private Integer page;
	private Integer size;

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
		response.getWriter().write(
				"{'count':" + senderDao.getPageCounts() + ",'list':"
						+ senderDao.getUsableOrders(page, size).toString()
						+ "}");
	}
}
