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
import zx.ffts.dao.chenkai.TsMenuDao;
import zx.ffts.dao.chenkai.TsMessageDao;
import zx.ffts.dao.chenkai.TsRestaurantDao;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.domain.chenkai.TsMenu;



public class ChenKaiTsMenuAction extends BaseAction {
	
	private TsMenuDao mymenu=new TsMenuDao();
	
	//获得所有菜单的集合
	public void getMenuList() throws IOException{
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
		menulist=mymenu.getMenuList(page, row);		
		int num=0;
		if (mymenu.menuCount()%row==0) {
			num=mymenu.menuCount()/row;
		}else{
			num=mymenu.menuCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", menulist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	//获得所有菜单的集合通过店家
	public void getAllMenuByRest()throws IOException{
		Integer murtid=Integer.parseInt(req.getParameter("murtid"));
		menulist=mymenu.getAllMenuByRest(murtid);		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", menulist);
		out.write(json.toString());
		out.flush();
		out.close();
	}

	//下载所有菜单
	public void WriteMenu() throws Exception{
		// 设置文件名
		String fname = "菜单数据表.xls";
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
		WritableSheet st = wb.createSheet("所有菜单数据", 0);
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
		Label labTitle = new Label(0, 0, "菜单数据", wcf);
		st.addCell(labTitle);
		st.mergeCells(0, 0, 8, 0);// 合并单元格

		/*****************************************************************/

		// 从session中，取得list
		List<Map<String, Object>> list = mymenu.WriteMenu();     //加载所有的用户
		
		// 创建标签，用于显示数据
		Label labId = new Label(0, 1, "编号", wcf);
		Label labRtName = new Label(1, 1, "店名", wcf);
		Label labMuName = new Label(2, 1, "菜名", wcf);
		Label labPrice = new Label(3, 1, "价格", wcf);
		Label labPic = new Label(4, 1, "图片", wcf);
		Label labType = new Label(5, 1, "类型", wcf);
		Label labDesc = new Label(6, 1, "描述", wcf);
		Label labSale = new Label(7, 1, "销量", wcf);
		Label labStatus = new Label(8, 1, "销售状态", wcf);
	
		
		// 把标签添加到表单中
		st.addCell(labId);
		st.addCell(labRtName);
		st.addCell(labMuName);
		st.addCell(labPrice);
		st.addCell(labPic);
		st.addCell(labType);
		st.addCell(labDesc);
		st.addCell(labSale);
		st.addCell(labStatus);
		
		
		for (int i = 0; i < list.size(); i++) {// 对list循环
			
			Map<String, Object> m = list.get(i);
			Label id = new Label(0, i + 2, m.get("MUID").toString(), wcf);
			Label rtname = new Label(1, i + 2, m.get("MRTNAME").toString(), wcf);
			Label muname = new Label(2, i + 2, m.get("MUNAME").toString(), wcf);
			Label price = new Label(3, i + 2, m.get("MUPRICE").toString(), wcf);
			Label pic = new Label(4, i + 2, m.get("MUPIC").toString(), wcf);
			Label type = new Label(5, i + 2, m.get("MUTYPE").toString(), wcf);
			Label desc = new Label(6, i + 2, m.get("MUDESC").toString(), wcf);
			Label sale = new Label(7, i + 2, m.get("MUSALE").toString(), wcf);
			String statustype="";
			if(m.get("MUSTATUS").toString().equals("0")){
				statustype="正在销售";
			}else if(m.get("MUSTATUS").toString().equals("1")){
				statustype="已售完";
			}
			  
			Label status = new Label(8, i + 2, statustype, wcf);
			
			st.addCell(id);
			st.addCell(rtname);
			st.addCell(muname);
			st.addCell(price);
			st.addCell(pic);
			st.addCell(type);
			st.addCell(desc);
			st.addCell(sale);
			st.addCell(status);
		}
		wb.write();
		wb.close();
		out.close();
	}
	//获得所有菜单的集合
	public void getAllMenu() throws IOException{
		menulist=mymenu.getAllMenu();		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", menulist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//删除菜单
	public void deleMenu(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		mymenu.deleteMenu(id);
	}

	//通过id查询某个菜单
	public void FindMenubyId() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsMenu ts=mymenu.findMenuById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("menu", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个菜单
	public String UpdaMenu() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer muid=Integer.parseInt(req.getParameter("muid"));
		String murtid=req.getParameter("murtid");
		String muname=req.getParameter("muname");
		String muprice=req.getParameter("muprice");
		String pic=req.getParameter("pic");
		String mutype=req.getParameter("mutype");
		String mudesc=req.getParameter("mudesc");
		String musale=req.getParameter("musale");
		String mustatus=req.getParameter("mustatus");

		
		System.out.println(muid+"\t"+murtid+"\t"+muname+"\t"+muprice+"\t"+pic+"\t"+mutype+"\t"+mudesc+"\t"+musale+"\t"+mustatus);
		if (photoFileName==null) {
			mymenu.updateMenu(Integer.parseInt(murtid), muname, Double.parseDouble(muprice), pic, mutype, mudesc, Integer.parseInt(musale), Integer.parseInt(mustatus), muid);
		}else{	
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			mymenu.updateMenu(Integer.parseInt(murtid), muname,  Double.parseDouble(muprice), src, mutype, mudesc, Integer.parseInt(musale), Integer.parseInt(mustatus), muid);
		}
		return "success";
	}
}
