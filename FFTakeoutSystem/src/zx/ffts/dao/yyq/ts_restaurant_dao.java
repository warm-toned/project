package zx.ffts.dao.yyq;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import zx.ffts.dao.DataDao;
import zx.ffts.dao.pain.SenderDao;
import zx.ffts.dao.xiong.OrderFunctionDao;

public class ts_restaurant_dao extends DataDao {
	// 加载所有的商店信息
	public List<Map<String, Object>> getShopList(pageinfo info) {
		String sql = "select * from (select s.*,rownum r from ts_restaurant s where rownum<=?)re where re.r>?";
		return getMapList(sql, info.getEnd(), info.getStar());

	}

	// 获得商店总数
	public int ShopCount() {
		String sql = "select count(*) from ts_restaurant";
		return scalarNumber(sql);
	}

	// 根据商家id加载所以的菜单信息
	public List<Map<String, Object>> getMenuList(Integer rtid) {
		String sql = "select ts_menu.*,row_number()over(partition by mutype order by mutype) rm from ts_menu where murtid=?";
		return getMapList(sql, rtid);
	}

	// 根据商家id加载所有的菜单类型
	public List<Map<String, Object>> getMenuType(Integer rtid) {
		String sql = "select mutype from ts_menu  where murtid=? group by mutype order by mutype";
		return getMapList(sql, rtid);
	}

	// 根据id查询商店的详细信息
	public Map<String, Object> getshopInfo(Integer rtid) {
		String sql = "select * from  ts_restaurant where rtid=?";
		return getObject(sql, rtid);
	}

	// 店铺评价
	public List<Map<String, Object>> ShopMessage(Integer mrtid) {
		String sql = "select m.mid,m.mcontent,m.mdate,u.username as mm from ts_message m,ts_user u where m.mid=u.userid and  m.mrtid=?";
		return getMapList(sql, mrtid);

	}

	// 根据菜的id查看所有评价
	public List<Map<String, Object>> MenuMessage(Integer muid, pageinfo info) {
		String sql = "select * from (select u.username as mm,u.photo,m.mmcontent,m.mmdate,m.mmscore,rownum r from ts_menumsg m,ts_user u,ts_menu n where u.userid=m.mmuserid and n.muid=m.mmmuid and n.muid=? and rownum<=?) t where t.r>?";
		return getMapList(sql, muid, info.getEnd(), info.getStar());

	}

	// 查询所有的菜单评价总数
	public int MenuMessCount(Integer muid) {
		String sql = "select count(*) from ts_menumsg where mmmuid=?";
		return scalarNumber(sql, muid);
	}

	@Test
	public void test() {
		System.out.println(new SenderDao().getUsableOrders(1, 10));
	}

	// 根据菜单id查看商店现象信息
	public Map<String, Object> GetMenuInfo(Integer muid) {
		String sql = "SELECT * FROM TS_MENU WHERE MUID=?";
		return getObject(sql, muid);
	}

	
}
