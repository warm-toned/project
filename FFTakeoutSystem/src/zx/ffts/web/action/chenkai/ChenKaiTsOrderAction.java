package zx.ffts.web.action.chenkai;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import zx.ffts.dao.chenkai.TsOrderDao;
import zx.ffts.domain.chenkai.TsOrder;


import net.sf.json.JSONObject;


public class ChenKaiTsOrderAction extends BaseAction {
	
	private TsOrderDao myorder=new TsOrderDao();
	
	//获得所有订单的集合
	public void getOrderList() throws IOException{
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
		olist=myorder.getOrderList(page, row);	
		int num=0;
		if (myorder.orderCount()%row==0) {
			num=myorder.orderCount()/row;
		}else{
			num=myorder.orderCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", olist);
		
		out.write(json.toString());
		out.flush();
		out.close();
	}
	

	//获得所有订单的集合
	public void getAllOrder() throws IOException{
		orderlist=myorder.getAllOrder();		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", orderlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//删除订单
	public void deleOrder(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		myorder.deleteOrder(id);
	}

	//通过id查询某个订单
	public void FindOrderbyId() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsOrder ts=myorder.findOrderById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("order", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个订单
	public String UpdaOrder() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer oid=Integer.parseInt(req.getParameter("oid"));
		String ouserid=req.getParameter("ouserid");
		String omuid=req.getParameter("omuid");
		String ortid=req.getParameter("ortid");
		String ocount=req.getParameter("ocount");
		String osender=req.getParameter("osender");
		String odate=req.getParameter("odate");
		String ouuid=req.getParameter("ouuid");
		String ostatus=req.getParameter("ostatus");

		myorder.updateOrder(Integer.parseInt(ouserid), Integer.parseInt(omuid), Integer.parseInt(ortid), Integer.parseInt(ocount),  Integer.parseInt(osender), odate, ouuid, Integer.parseInt(ostatus),oid);
		
		return "success";
	}
	
	//下载所有订单 
	public void WriteOrder() throws Exception{
		// 设置文件名
		String fname = "订单数据表.xls";
		String fileName = URLEncoder.encode(fname, "utf-8");
		// 弹出下载的面板---用于下载xls文件

		res.setContentType("application/vnd.ms-excle");
		res.setHeader("Content-disposition", "attachment;fileName="
				+ fileName);
		// 产生输出流，用于将服务端的信息，以电子文档的方式，输出到客户端
		OutputStream out = res.getOutputStream();

		// 产生电子文档
		WritableWorkbook wb = Workbook.createWorkbook(out);
		// 产生表单
		WritableSheet st = wb.createSheet("所有订单数据", 0);
		/************************* 设置显示样式 ****************************************/
		st.getSettings().setDefaultColumnWidth(14); // 设置列宽
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
				WritableFont.BOLD);// 创建可以用输出的字体格式(字体类型，字体大小，字体样式)

		// 创建一种显示样式，用于设置单元格，以什么样式来显示数据
		WritableCellFormat wcf = new WritableCellFormat(wf);// 设置单元格里面的内容，以什么字体来显示
		wcf.setAlignment(Alignment.CENTRE);// 设置显示方式
		wcf.setWrap(true);// 当内容显示不下的时候，自动换行
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);// 设置边框
		/*****************************************************************/

		/************************ 增加标题行 *****************************************/
		Label labTitle = new Label(0, 0, "订单数据", wcf);
		st.addCell(labTitle);
		st.mergeCells(0, 0, 8, 0);// 合并单元格

		/*****************************************************************/

		// 从session中，取得list
		List<Map<String, Object>> list = myorder.WriteOrder();     //加载所有的用户
	
		// 创建标签，用于显示数据
		Label labId = new Label(0, 1, "编号", wcf);
		Label labUsName = new Label(1, 1, "下单用户", wcf);
		Label labReName= new Label(2, 1, "店名", wcf);
		Label labMuName = new Label(3, 1, "菜名", wcf);
		Label labCount = new Label(4, 1, "数量", wcf);
		Label labSender = new Label(5, 1, "配送员", wcf);
		Label labDate = new Label(6, 1, "下单时间", wcf);
		Label labOuuid = new Label(7, 1, "OUUID", wcf);
		Label labStatus = new Label(8, 1, "订单状态", wcf);

		
		
		// 把标签添加到表单中
		st.addCell(labId);
		st.addCell(labUsName);
		st.addCell(labReName);
		st.addCell(labMuName);
		st.addCell(labCount);
		st.addCell(labSender);
		st.addCell(labDate);
		st.addCell(labOuuid);
		st.addCell(labStatus);
	
		
		for (int i = 0; i < list.size(); i++) {// 对list循环
			
			Map<String, Object> m = list.get(i);
			Label id = new Label(0, i + 2, m.get("OID").toString(), wcf);
			Label usname = new Label(1, i + 2, m.get("OUSERNAME").toString(), wcf);
			Label rtname = new Label(2, i + 2, m.get("ORTNAME").toString(), wcf);
			Label muname = new Label(3, i + 2, m.get("OMUNAME").toString(), wcf);
			Label count = new Label(4, i + 2, m.get("OCOUNT").toString(), wcf);
			Label sender = new Label(5, i + 2, m.get("OSENDERNAME").toString(), wcf);
			Label date = new Label(6, i + 2, m.get("ODATE").toString(), wcf);
			Label ouuid = new Label(7, i + 2, m.get("OUUID").toString(), wcf);
			String statustype="";
			if(m.get("OSTATUS").toString().equals("0")){
				statustype="购物车中";
			}else if(m.get("OSTATUS").toString().equals("1")){
				statustype="已下单（未支付）";
			}else if(m.get("OSTATUS").toString().equals("2")){
				statustype="已支付";	
			}else if(m.get("OSTATUS").toString().equals("3")){
				statustype="商家已接单";
			}else if(m.get("OSTATUS").toString().equals("4")){
				statustype="配送中";
			}else if(m.get("OSTATUS").toString().equals("5")){
				statustype="订单完成";
			}
			  
			Label status = new Label(8, i + 2, statustype, wcf);
			
			st.addCell(id);
			st.addCell(usname);
			st.addCell(rtname);
			st.addCell(muname);
			st.addCell(count);
			st.addCell(sender);
			st.addCell(date);
			st.addCell(ouuid);
			st.addCell(status);
		}
		wb.write();
		wb.close();
		out.close();
	}
}
