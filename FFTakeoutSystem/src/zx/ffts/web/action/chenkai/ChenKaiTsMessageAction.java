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
import zx.ffts.dao.chenkai.TsMessageDao;
import zx.ffts.dao.chenkai.TsRestaurantDao;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.domain.chenkai.TsMessage;



public class ChenKaiTsMessageAction extends BaseAction {
	
	private TsMessageDao mymess=new TsMessageDao();
	
	//获得所有店家评论的集合
	public void getMessList() throws IOException{
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
		messlist=mymess.getMessageList(page, row);		
		int num=0;
		if (mymess.MessageCount()%row==0) {
			num=mymess.MessageCount()/row;
		}else{
			num=mymess.MessageCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", messlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	
	//删除店家评论
	public void deleMess(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		mymess.deleteMessage(id);
	}

	//通过id查询某个店家评论
	public void FindMessbyId() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsMessage ts=mymess.findMessageById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("mess", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个店家评论
	public String UpdaMess() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer mid=Integer.parseInt(req.getParameter("mid"));
		String muserid=req.getParameter("muserid");
		String mrtid=req.getParameter("mrtid");
		String mcontent=req.getParameter("mcontent");
		String mdate=req.getParameter("mdate");
	
		mymess.updateMessage(Integer.parseInt(muserid), Integer.parseInt(mrtid), mcontent, mdate, mid);
		return "success";
	}
	
}
