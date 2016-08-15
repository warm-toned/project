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
import zx.ffts.dao.chenkai.TsRestaurantDao;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.domain.chenkai.TsRestaurant;



public class ChenKaiTsRestaurantAction extends BaseAction {
	
	private TsRestaurantDao myrest=new TsRestaurantDao();
	
	//获得所有商家的集合（分页）
	public void getRestList() throws IOException{
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
		restlist=myrest.getRestList(page, row);		
		int num=0;
		if (myrest.restCount()%row==0) {
			num=myrest.restCount()/row;
		}else{
			num=myrest.restCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", restlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//获得所有商家的集合
	public void getAllRest() throws IOException{
		restlist=myrest.getAllRest();		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", restlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//下载所有商家信息
	public void WriteRest() throws Exception{
		// 设置文件名
		String fname = "店家数据表.xls";
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
		WritableSheet st = wb.createSheet("所有商店数据", 0);
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
		Label labTitle = new Label(0, 0, "用户数据", wcf);
		st.addCell(labTitle);
		st.mergeCells(0, 0, 8, 0);// 合并单元格

		/*****************************************************************/

		// 从session中，取得list
		List<Map<String, Object>> list = myrest.WriteRest();     //加载所有的商店
		
		// 创建标签，用于显示数据
		Label labId = new Label(0, 1, "编号", wcf);
		Label labName = new Label(1, 1, "商店名称", wcf);
		Label labAddr = new Label(2, 1, "地址", wcf);
		Label labOwner = new Label(3, 1, "店主名", wcf);
		Label labPic = new Label(4, 1, "图片", wcf);
		Label labContent = new Label(5, 1, "公告", wcf);
		Label labDate = new Label(6, 1, "开店时间", wcf);
		Label labOnbuz = new Label(7, 1, "营业时间", wcf);
		Label labStatus = new Label(8, 1, "营业状态", wcf);
		
		
		
		// 把标签添加到表单中
		st.addCell(labId);
		st.addCell(labName);
		st.addCell(labAddr);
		st.addCell(labOwner);
		st.addCell(labPic);
		st.addCell(labContent);
		st.addCell(labDate);
		st.addCell(labOnbuz);
		st.addCell(labStatus);
		
		for (int i = 0; i < list.size(); i++) {// 对list循环
			
			Map<String, Object> m = list.get(i);
			Label id = new Label(0, i + 2, m.get("RTID").toString(), wcf);
			Label name = new Label(1, i + 2, m.get("RTNAME").toString(), wcf);
			Label addr = new Label(2, i + 2, m.get("RTADDR").toString(), wcf);
			Label owner = new Label(3, i + 2, m.get("OWNER").toString(), wcf);
			Label pic = new Label(4, i + 2, m.get("RTPIC").toString(), wcf);
			Label content = new Label(5, i + 2, m.get("RTCONTENT").toString(), wcf);
			Label date = new Label(6, i + 2, m.get("RTDATE").toString(), wcf);
			Label onbuz = new Label(7, i + 2, m.get("RTONBUZ").toString(), wcf);
			String statustype="";
			if(m.get("RTSTATUS").toString().equals("0")){
				statustype="真正营业";
			}else if(m.get("RTSTATUS").toString().equals("1")){
				statustype="休息中";
			}
			  
			Label status = new Label(8, i + 2, statustype, wcf);
			
			st.addCell(id);
			st.addCell(name);
			st.addCell(addr);
			st.addCell(owner);
			st.addCell(pic);
			st.addCell(content);
			st.addCell(date);
			st.addCell(onbuz);
			st.addCell(status);

		}
		wb.write();
		wb.close();
		out.close();
	}
	
	//添加店家
	public String addRest() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String rtname=req.getParameter("rtname");
		String rtaddr=req.getParameter("rtaddr");
		String rtowner=req.getParameter("rtowner");
		//String rtpic=req.getParameter("rtpic");
		String rtcontent=req.getParameter("rtcontent");
		//String rtdate=req.getParameter("rtdate");
		String rtonbuz=req.getParameter("rtonbuz");
		String rtstatus=req.getParameter("rtstatus");
	
		if (photoFileName==null) {
			myrest.addRest(rtname, rtaddr, Integer.parseInt(rtowner), null, rtcontent, rtonbuz, rtstatus);	
		}else{
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			myrest.addRest(rtname, rtaddr, Integer.parseInt(rtowner), src, rtcontent, rtonbuz, rtstatus);	
		}
		return "success";
	}
	
	//删除店家
	public void deleRest(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		myrest.deleteRest(id);
	}

	//通过id查询某个店家
	public void FindRestbyId() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsRestaurant ts=myrest.findRestById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rest", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个店家的信息
	public String UpdaRest() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer rtid=Integer.parseInt(req.getParameter("rtid"));
		String rtname=req.getParameter("rtname");
		String rtaddr=req.getParameter("rtaddr");
		String rtowner=req.getParameter("rtowner");
		String rtcontent=req.getParameter("rtcontent");
		String rtdate=req.getParameter("rtdate");
		String rtonbuz=req.getParameter("rtonbuz");
		String rtstatus=req.getParameter("rtstatus");
		String pic=req.getParameter("pic");
	
		if (photoFileName==null) {
			myrest.updateRest(rtname, rtaddr, rtowner, pic, rtcontent, rtdate, rtonbuz, Integer.parseInt(rtstatus), rtid);
		}else{	
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			myrest.updateRest(rtname, rtaddr, rtowner, src, rtcontent, rtdate, rtonbuz, Integer.parseInt(rtstatus), rtid);	
		}
		return "success";
	}
	
}
