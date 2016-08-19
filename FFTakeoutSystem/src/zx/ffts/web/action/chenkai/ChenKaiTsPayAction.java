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
import zx.ffts.dao.chenkai.TsPayDao;
import zx.ffts.dao.chenkai.TsRestaurantDao;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.domain.chenkai.TsPay;



public class ChenKaiTsPayAction extends BaseAction {
	
	private TsPayDao mypay=new TsPayDao();
	
	//获得所有订单支付方式
	public void getPayList() throws IOException{
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
		paylist=mypay.getPayList(page, row);
		int num=0;
		if (mypay.payCount()%row==0) {
			num=mypay.payCount()/row;
		}else{
			num=mypay.payCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", paylist);
		out.write(json.toString());
		out.flush();
		out.close();
	}

	//删除某个订单的支付方式
	public void delePay(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		mypay.deletePay(id);
	}

	//通过id查询某个订单的支付方式
	public void FindPaybyId() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsPay ts=mypay.findPayById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pay", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个订单的支付方式
	public String UpdaPay() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String pid=req.getParameter("pid");
		String ptype=req.getParameter("ptype");
		mypay.updatePay(ptype, Integer.parseInt(pid));
		return "success";
	}
	
}
