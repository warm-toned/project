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

import net.sf.json.JSONObject;
import zx.ffts.dao.chenkai.TsGiftDao;
import zx.ffts.dao.chenkai.TsRestaurantDao;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.domain.chenkai.TsGift;



public class ChenKaiTsGiftAction extends BaseAction {
	
	private TsGiftDao mygift=new TsGiftDao();
	
	//获得所有礼品
	public void getGiftList() throws IOException{
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
		giftlist=mygift.getGiftList(page, row);		
		int num=0;
		if (mygift.giftCount()%row==0) {
			num=mygift.giftCount()/row;
		}else{
			num=mygift.giftCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", giftlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//获得所有礼品的集合
	public void getAllGift() throws IOException{
		giftlist=mygift.getAllGift();		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", giftlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//添加礼品
	public String addGift() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String gname=req.getParameter("gname");
		String greqscore=req.getParameter("greqscore");
		String gsum=req.getParameter("gsum");
		String gdesc=req.getParameter("gdesc");

	
		if (photoFileName==null) {
			mygift.addGift(gname, null, Integer.parseInt(greqscore),Integer.parseInt(gsum), gdesc);
		}else{
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			mygift.addGift(gname, src, Integer.parseInt(greqscore),Integer.parseInt(gsum), gdesc);	
		}
		return "success";
	}
	
	//删除礼品
	public void deleGift(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		mygift.deleteGift(id);
	}

	//通过id查询某个礼品
	public void findGiftById() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsGift ts=mygift.findGiftById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("gift", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个礼品的信息
	public String UpdaGift() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer gid=Integer.parseInt(req.getParameter("gid"));
		String gname=req.getParameter("gname");
		String greqscore=req.getParameter("greqscore");
		String gcount=req.getParameter("gcount");
		String gsum=req.getParameter("gsum");
		String gdesc=req.getParameter("gdesc");
		String pic=req.getParameter("pic");
	
		if (photoFileName==null) {
			mygift.updateGift(gname, pic, Integer.parseInt(greqscore), Integer.parseInt(gcount), Integer.parseInt(gsum), gdesc, gid);
		}else{	
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			mygift.updateGift(gname, src, Integer.parseInt(greqscore), Integer.parseInt(gcount), Integer.parseInt(gsum), gdesc, gid);	
		}
		return "success";
	}
	
	//下载所有礼品
	public void WriteGift() throws Exception{
		// 设置文件名
		String fname = "礼品数据表.xls";
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
		WritableSheet st = wb.createSheet("所有礼品数据", 0);
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
		Label labTitle = new Label(0, 0, "礼品数据", wcf);
		st.addCell(labTitle);
		st.mergeCells(0, 0, 7, 0);// 合并单元格

		/*****************************************************************/

		// 从session中，取得list
		List<Map<String, Object>> list = mygift.WriteGift();     //加载所有的用户
	
		// 创建标签，用于显示数据
		Label labId = new Label(0, 1, "编号", wcf);
		Label labName = new Label(1, 1, "礼品名称", wcf);
		Label labPic= new Label(2, 1, "图片", wcf);
		Label labReqScore = new Label(3, 1, "所需积分", wcf);
		Label labCount = new Label(4, 1, "已兑数量", wcf);
		Label labSum = new Label(5, 1, "总库存数量", wcf);
		Label labDesc = new Label(6, 1, "描述", wcf);
		
		
		
		// 把标签添加到表单中
		st.addCell(labId);
		st.addCell(labName);
		st.addCell(labPic);
		st.addCell(labReqScore);
		st.addCell(labCount);
		st.addCell(labSum);
		st.addCell(labDesc);
	
		
		for (int i = 0; i < list.size(); i++) {// 对list循环
			
			Map<String, Object> m = list.get(i);
			Label id = new Label(0, i + 2, m.get("GID").toString(), wcf);
			Label name = new Label(1, i + 2, m.get("GNAME").toString(), wcf);
			Label pic = new Label(2, i + 2, m.get("GPIC").toString(), wcf);
			Label reqscore = new Label(3, i + 2, m.get("GREQSCORE").toString(), wcf);
			Label count = new Label(4, i + 2, m.get("GCOUNT").toString(), wcf);
			Label sum = new Label(5, i + 2, m.get("GSUM").toString(), wcf);
			Label desc = new Label(6, i + 2, m.get("GDESC").toString(), wcf);
			
			st.addCell(id);
			st.addCell(name);
			st.addCell(pic);
			st.addCell(reqscore);
			st.addCell(count);
			st.addCell(sum);
			st.addCell(desc);
		
		}
		wb.write();
		wb.close();
		out.close();
	}
}
