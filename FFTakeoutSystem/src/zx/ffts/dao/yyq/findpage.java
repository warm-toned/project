package zx.ffts.dao.yyq;

import java.util.List;
import java.util.Map;

public class findpage {
	//商店分页
  public pagelist findShop(int nowpage){
	  Ts_restaurant_dao dd=new Ts_restaurant_dao();
	  int ShopCount=dd.ShopCount();
	  pageinfo info= new pageinfo();
	  info.setNowpage(nowpage);
	  info.setPagenum(5);
	  info.setSumnum(ShopCount);
	  
	 List<Map<String,Object>> li=dd.getShopList(info);
	 pagelist list=new pagelist(info,li);
     return list;
  }
  //菜单评价分页
  public pagelist findMenuMess(Integer muid,int nowpage){
	  Ts_restaurant_dao dd=new Ts_restaurant_dao();
	  int MenuMessCount=dd.MenuMessCount(muid);
	  System.out.println("muid"+muid);
	  pageinfo info= new pageinfo();
	  info.setNowpage(nowpage);
	  info.setPagenum(3);
	  info.setSumnum(MenuMessCount);
	  
	 List<Map<String,Object>> li=dd.MenuMessage(muid, info);
	 pagelist list=new pagelist(info,li);
     return list;
  }
 
}
