package zx.ffts.web.action.chenkai;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;
import zx.ffts.dao.chenkai.TsMenumsgDao;
import zx.ffts.dao.chenkai.TsMessageDao;
import zx.ffts.dao.chenkai.TsRestaurantDao;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.domain.chenkai.TsMenuMsg;



public class ChenKaiTsMenuMsgAction extends BaseAction {
	
	private TsMenumsgDao mymmsg=new TsMenumsgDao();
	
	//获得所有菜单评论的集合
	public void getMenuMsgList() throws IOException{
		String pageStr = req.getParameter("page");
		String rowStr = req.getParameter("rows");
		
		Integer page=1;
		Integer row=5;
		if (pageStr!=null&&!"".equals(pageStr)) {
			page=Integer.parseInt(pageStr);
		}
		if (rowStr!=null&&!"".equals(rowStr)) {
			row=Integer.parseInt(rowStr);
		}	
		menumsglist=mymmsg.getMenuMsgList(page, row);
		int num=0;
		if (mymmsg.MenuMsgCount()%row==0) {
			num=mymmsg.MenuMsgCount()/row;
		}else{
			num=mymmsg.MenuMsgCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", menumsglist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	
	//删除菜单评论
	public void deleMenuMsg(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		mymmsg.deleteMenuMsg(id);
	}

	//通过id查询某个菜单评论
	public void FindMenuMsgbyId() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsMenuMsg ts=mymmsg.findMenuMsgById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("menumsg", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个菜单评论
	public String UpdaMenuMsg() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer mmid=Integer.parseInt(req.getParameter("mmid"));
		String mmuserid=req.getParameter("mmuserid");
		String mmscore=req.getParameter("mmscore");
		String mmcontent=req.getParameter("mmcontent");
		String mmdate=req.getParameter("mmdate");
		String mmmuid=req.getParameter("mmmuid");
	
		mymmsg.updateMenuMsg(Integer.parseInt(mmuserid), Integer.parseInt(mmmuid), mmcontent, Integer.parseInt(mmscore), mmdate, mmid);
		return "success";
	}
	
}
