package zx.ffts.web.action.yyq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import zx.ffts.dao.xiong.OrderFunctionDao;
import zx.ffts.dao.yyq.findpage;
import zx.ffts.dao.yyq.pagelist;
import zx.ffts.dao.yyq.ts_restaurant_dao;
import zx.ffts.domain.User;
import zx.ffts.utils.PageBean;

public class YYQAction {
	private HttpServletRequest req = ServletActionContext.getRequest();
	private HttpServletResponse res = ServletActionContext.getResponse();
	private HttpSession session = req.getSession();
	private ts_restaurant_dao dao = new ts_restaurant_dao();
	private OrderFunctionDao functionDao = new OrderFunctionDao();
	private findpage pg = new findpage();
	private Integer rtid;
	private Integer muid;
	private Integer cp;

	public Integer getCp() {
		return cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	public Integer getMuid() {
		return muid;
	}

	public void setMuid(Integer muid) {
		this.muid = muid;
	}

	public Integer getRtid() {
		return rtid;
	}

	public void setRtid(Integer rtid) {
		this.rtid = rtid;
	}

	// 加载所有商店信息
	public String ShopList() {
		Integer nowpage = 1;
		if (cp != null && cp != 0) {
			nowpage = cp;
		} else {
			cp = 1;
		}
		pagelist lis = pg.findShop(nowpage);
		req.setAttribute("shop", lis);
		return "ShopList";
	}

	// 加载对应店铺的商店信息
	public String MenuList() {
		if (cp == null) {
			cp = 1;
		}
		List<Map<String, Object>> menu = dao.getMenuList(rtid);
		User user = (User) session.getAttribute("user");
		Integer userid = 0;
		if (user != null) {
			userid = user.getUserid();
		}
		List<Map<String, Object>> counts = functionDao.getUnorderedCount(
				userid, rtid);
		// 菜单类型
		List<Map<String, Object>> type = dao.getMenuType(rtid);
		Map<String, Object> shopinfo = dao.getshopInfo(rtid);
		shopinfo.put("score", functionDao.getRestaurantScore(rtid));
		List<Map<String, Object>> newMenu = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> m1 : menu) {
			String muids = m1.get("muid").toString();
			m1.put("haoping", functionDao.getHaoPing(Integer.parseInt(muids)));
			for (Map<String, Object> m2 : counts) {
				Object omuid = m2.get("omuid");
				if (omuid != null
						&& omuid.toString().equals(m1.get("muid").toString())) {
					m1.put("ocount", m2.get("ocount"));
				}
			}
			newMenu.add(m1);
		}
		PageBean<Map<String, Object>> bean = functionDao.getPageBean(
				"select t.*,to_char(mdate,'yyyy-mm-dd hh24:mi:ss') mtime from ts_message t", null,
				new String[] { "mdate" }, " and mrtid=? ", cp, 10, rtid);
		List<Map<String, Object>> beanList = bean.getBeanList();
		if (beanList.size() > 0) {
			functionDao.getUserInfoToList(beanList);
		}
		req.setAttribute("MenuList", newMenu);
		req.setAttribute("MenuType", type);
		req.setAttribute("shopById", shopinfo);
		req.setAttribute("shopid", rtid);
		req.setAttribute("message", bean);
		return "MenuList";
	}

	// 菜单评价
	public String getMenuItem() {
		Integer nowpage = 1;
		if (cp != null && cp != 0) {
			nowpage = cp;
		} else {
			cp = 1;
		}
		pagelist list = pg.findMenuMess(muid, nowpage);
		Map<String, Object> info = dao.GetMenuInfo(muid);
		info.put("haoping", functionDao.getHaoPing(muid));
		User user = (User) session.getAttribute("user");
		Integer number = null;
		if (user != null) {
			number = functionDao.getOneOrderNumber(user.getUserid(), muid);
		}
		info.put("ocount", number);
		req.setAttribute("menuItem", info);
		req.setAttribute("menuMess", list);
		return "MenuMessage";
	}
}
