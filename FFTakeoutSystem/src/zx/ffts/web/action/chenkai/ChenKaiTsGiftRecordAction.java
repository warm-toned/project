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
import jxl.write.biff.RowsExceededException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;
import zx.ffts.dao.chenkai.TsGiftRecordDao;
import zx.ffts.dao.chenkai.TsMenumsgDao;
import zx.ffts.dao.chenkai.TsMessageDao;
import zx.ffts.dao.chenkai.TsRestaurantDao;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.domain.chenkai.TsGiftRecord;



public class ChenKaiTsGiftRecordAction extends BaseAction {
	
	private TsGiftRecordDao mygrec=new TsGiftRecordDao();
	
	//获得所有礼品兑换记录的集合
	public void getGiftRecordList() throws IOException{
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
		greclist=mygrec.getTsGiftRecordList(page, row);
		int num=0;
		if (mygrec.GiftRecordCount()%row==0) {
			num=mygrec.GiftRecordCount()/row;
		}else{
			num=mygrec.GiftRecordCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", greclist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	
	//删除礼品兑换记录
	public void deleGiftRecord(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		mygrec.deleteGiftRecord(id);
	}

	//通过id查询某个礼品兑换记录
	public void FindGiftRecordbyId() throws Exception{
		
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsGiftRecord ts=mygrec.findGiftRecordById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("grec", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个礼品兑换记录
	public String UpdaGiftRecord() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer grid=Integer.parseInt(req.getParameter("grid"));
		String grgid=req.getParameter("grgid");
		String gruserid=req.getParameter("gruserid");
		String grnum=req.getParameter("grnum");
		String grdate=req.getParameter("grdate");
		String grstatus=req.getParameter("grstatus");
	
		System.out.println(grid+"\t"+grgid+"\t"+gruserid+"\t"+grnum+"\t"+grdate+"\t"+grstatus);
		mygrec.updateGiftRecord(Integer.parseInt(grgid), Integer.parseInt(gruserid), Integer.parseInt(grnum), grdate, Integer.parseInt(grstatus), grid);
		return "success";
	}
	
	//下载所有礼品记录
	public void WriteGiftRec() throws Exception{
		// 设置文件名
		String fname = "礼品兑换记录表.xls";
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
		WritableSheet st = wb.createSheet("所有礼品兑换记录", 0);
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
		Label labTitle = new Label(0, 0, "礼品兑换数据", wcf);
		st.addCell(labTitle);
		st.mergeCells(0, 0, 5, 0);// 合并单元格

		/*****************************************************************/

		// 从session中，取得list
		List<Map<String, Object>> list = mygrec.WriteGiftRec();     //加载所有的用户
		
		// 创建标签，用于显示数据
		Label labId = new Label(0, 1, "编号", wcf);
		Label labGName = new Label(1, 1, "礼品", wcf);
		Label labUName = new Label(2, 1, "兑换人", wcf);
		Label labNum = new Label(3, 1, "兑换数量", wcf);
		Label labDate = new Label(4, 1, "兑换日期", wcf);
		Label labStatus = new Label(5, 1, "是否派送", wcf);
		
		
		// 把标签添加到表单中
		st.addCell(labId);
		st.addCell(labGName);
		st.addCell(labUName);
		st.addCell(labNum);
		st.addCell(labDate);
		st.addCell(labStatus);
		
		
		for (int i = 0; i < list.size(); i++) {// 对list循环
			
			Map<String, Object> m = list.get(i);
			Label id = new Label(0, i + 2, m.get("GRID").toString(), wcf);
			Label gname = new Label(1, i + 2, m.get("GRGNAME").toString(), wcf);
			Label uname = new Label(2, i + 2, m.get("GRUSERNAME").toString(), wcf);
			Label num = new Label(3, i + 2, m.get("GRNUM").toString(), wcf);
			Label date = new Label(4, i + 2, m.get("GRDATE").toString(), wcf);
			
			String statustype="";
			if(m.get("GRSTATUS").toString().equals("0")){
				statustype="已配送";
			}else if(m.get("GRSTATUS").toString().equals("1")){
				statustype="未配送";
			}
			  
			Label status = new Label(5, i + 2, statustype, wcf);
			
			st.addCell(id);
			st.addCell(gname);
			st.addCell(uname);
			st.addCell(num);
			st.addCell(date);
			st.addCell(status);

		}
		wb.write();
		wb.close();
		out.close();
	}
}
