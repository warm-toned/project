package zx.ffts.web.action.yyq;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import zx.ffts.dao.yyq.GiftDao;
import zx.ffts.domain.User;

public class GiftAction {
	HttpServletRequest req = ServletActionContext.getRequest();
	HttpServletResponse res = ServletActionContext.getResponse();
	HttpSession session;
	GiftDao gdao = new GiftDao();

	// 礼品信息
	public String getgiftList() {
		List<Map<String, Object>> gift = gdao.gift();
		session = req.getSession();
		User user = (User) session.getAttribute("user");// 获得登录用户id
		Integer userScore = gdao.UserScore(user.getUserid());// 根据用户id获得积分
		req.setAttribute("userScore", userScore);
		req.setAttribute("gift", gift);
		return "gifts";
	}

	// 积分兑换
	public void change() throws Exception {
		session = req.getSession();
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		Integer gid = Integer.parseInt(req.getParameter("gid"));// 商品id
		User user = (User) session.getAttribute("user");// 获得用户id
		if (gdao.UserScore(user.getUserid()) >= gdao.GiftScore(gid)) {
			int Uscore = gdao
					.upUserScore(gdao.GiftScore(gid), user.getUserid());// 修改用户积分
			int gcount = gdao.upGiftcount(gid);// 修改礼品的兑换数
			int insChange = gdao.insChange(gid, user.getUserid());// 插入兑换记录表
			json.put("uscore", gdao.UserScore(user.getUserid()));// 兑换后的用户积分
			json.put("gcount", gdao.gcount(gid));// 兑换后的已兑换个数
			json.put("res", 1);
		} else {
			json.put("res", -1);
		}
		out.write(json.toString());
		out.flush();
		out.close();

	}

	// 兑换记录
	public String getrecord() {
		session = req.getSession();
		User user = (User) session.getAttribute("user");// 获得用户积分和id
		List<Map<String, Object>> record = gdao.giftrecord(user.getUserid());
		req.setAttribute("record", record);
		return "record";
	}
}
