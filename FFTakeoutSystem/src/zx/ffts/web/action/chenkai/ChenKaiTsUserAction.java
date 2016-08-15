package zx.ffts.web.action.chenkai;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.domain.chenkai.TsUser;
import zx.ffts.utils.PageBean;


public class ChenKaiTsUserAction extends BaseAction {
	
	private TsUserDao myuser=new TsUserDao();
	
	//登陆
	public String Login(){
		TsUser us=myuser.login(tsuser.getUsername());
		if (tsuser.getPwd().equals(us.getPwd())&&us.getAuthority()==4) {
			ses.setAttribute("user",us);
			return "success";
		}else if (tsuser.getPwd().equals(us.getPwd())&&us.getAuthority()==5) {
			ses.setAttribute("user",us);
			return "success";
		}else{
			req.setAttribute("error", "对不起，您的用户名，或密码不正确，或者权限不够");
			return "error";
		}	
	}
	
	//分页获得所有用户的集合（分页）
	public void getUserList(){
		try{
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
		 userlist=myuser.getUserList(page, row);	
		int num=0;
		if (myuser.userCount()%row==0) {
			num=myuser.userCount()/row;
		}else{
			num=myuser.userCount()/row+1;
		}
		PrintWriter out=res.getWriter();
		JSONObject json=new JSONObject();
		json.put("pages", page);
		json.put("total", num);	
		json.put("rows", userlist);
		out.write(json.toString());
		
		out.flush();
		out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//获得所有用户的集合
	public void getAllUser() throws IOException{
	
		userlist=myuser.getAllUser();		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", userlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//添加新用户
	public String addUser() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String username=req.getParameter("username");
		String pwd=req.getParameter("pwd");
		String tel=req.getParameter("tel");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		String realname=req.getParameter("realname");
		String balance=req.getParameter("balance");
		String gender=req.getParameter("gender");
		String authority=req.getParameter("authority");
	
	
		if (photoFileName==null) {
			myuser.addUser(username, pwd, tel, email, address, realname, Double.parseDouble(balance), gender, Integer.parseInt(authority), null);	
		}else{
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			myuser.addUser(username, pwd, tel, email, address, realname, Integer.parseInt(balance), gender, Integer.parseInt(authority), src);	
		}	
		return "success";
		
	}
	
	//删除用户
	public void deleUser(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		myuser.deleteUser(id);	
	}

	//通过id查询用户
	public void FindbyId() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsUser ts=myuser.findUserById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("user", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个用户的信息
	public String UpdaUser() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String userid=req.getParameter("userid");
		String username=req.getParameter("username");
		String pwd=req.getParameter("pwd");
		String tel=req.getParameter("tel");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		String realname=req.getParameter("realname");
		String balance=req.getParameter("balance");
		String score=req.getParameter("score");
		String regdate=req.getParameter("regdate");
		String gender=req.getParameter("gender");
		String authority=req.getParameter("authority");
		String pic=req.getParameter("pic");
	
		if (photoFileName==null) {
			myuser.updateUser(username, pwd, tel, email, address, realname,Double.parseDouble(balance), Integer.parseInt(score), gender, regdate, Integer.parseInt(authority), pic, Integer.parseInt(userid));
		}else{	
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			myuser.updateUser(username, pwd, tel, email, address, realname,Double.parseDouble(balance), Integer.parseInt(score), gender, regdate, Integer.parseInt(authority), src, Integer.parseInt(userid));	
		}
		return "success";
	}


	//产生用户表电子文档
	public void WriteUser() throws Exception{
		// 设置文件名
		String fname = "用户数据表.xls";
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
		WritableSheet st = wb.createSheet("所有用户数据", 0);
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
		st.mergeCells(0, 0, 12, 0);// 合并单元格

		/*****************************************************************/

		// 从session中，取得list
		List<Map<String, Object>> list = myuser.WriteUser();     //加载所有的用户
		
		// 创建标签，用于显示数据
		Label labId = new Label(0, 1, "编号", wcf);
		Label labName = new Label(1, 1, "用户姓名", wcf);
		Label labPwd = new Label(2, 1, "密码", wcf);
		Label labTel = new Label(3, 1, "电话号码", wcf);
		Label labEmail = new Label(4, 1, "电子邮件", wcf);
		Label labAddress = new Label(5, 1, "地址", wcf);
		Label labRealname = new Label(6, 1, "真实姓名", wcf);
		Label labBalance = new Label(7, 1, "余额", wcf);
		Label labScore = new Label(8, 1, "积分", wcf);
		Label labGender = new Label(9, 1, "性别", wcf);
		Label labRegdate = new Label(10, 1, "注册日期", wcf);
		Label labAuthority = new Label(11, 1, "权限", wcf);
		Label labPhoto = new Label(12, 1, "图片路径", wcf);
		
		
		// 把标签添加到表单中
		st.addCell(labId);
		st.addCell(labName);
		st.addCell(labPwd);
		st.addCell(labTel);
		st.addCell(labEmail);
		st.addCell(labAddress);
		st.addCell(labRealname);
		st.addCell(labBalance);
		st.addCell(labScore);
		st.addCell(labGender);
		st.addCell(labRegdate);
		st.addCell(labAuthority);
		st.addCell(labPhoto);
		
		for (int i = 0; i < list.size(); i++) {// 对list循环
			
			Map<String, Object> m = list.get(i);
			Label id = new Label(0, i + 2, m.get("USERID").toString(), wcf);
			Label name = new Label(1, i + 2, m.get("USERNAME").toString(), wcf);
			Label pwd = new Label(2, i + 2, m.get("PWD").toString(), wcf);
			Label tel = new Label(3, i + 2, m.get("TEL").toString(), wcf);
			Label email = new Label(4, i + 2, m.get("EMAIL").toString(), wcf);
			Label address = new Label(5, i + 2, m.get("ADDRESS").toString(), wcf);
			Label realname = new Label(6, i + 2, m.get("REALNAME").toString(), wcf);
			Label balance = new Label(7, i + 2, m.get("BALANCE").toString(), wcf);
			Label score = new Label(8, i + 2, m.get("SCORE").toString(), wcf);
			Label gender = new Label(9, i + 2, m.get("GENDER").toString(), wcf);
			Label regdate = new Label(10, i + 2, m.get("REGDATE").toString(), wcf);
			String statustype="";
			if(m.get("AUTHORITY").toString().equals("1")){
				statustype="普通用户";
			}else if(m.get("AUTHORITY").toString().equals("2")){
				statustype="配送员";
			}else if(m.get("AUTHORITY").toString().equals("3")){
				statustype="店主";	
			}else if(m.get("AUTHORITY").toString().equals("4")){
				statustype="普通管理员";
			}else if(m.get("AUTHORITY").toString().equals("5")){
				statustype="系统管理员";
			}
			  
			Label authority = new Label(11, i + 2, statustype, wcf);
			Label photo = new Label(12, i + 2, m.get("PHOTO").toString(), wcf);
			
			st.addCell(id);
			st.addCell(name);
			st.addCell(pwd);
			st.addCell(tel);
			st.addCell(email);
			st.addCell(address);
			st.addCell(realname);
			st.addCell(balance);
			st.addCell(score);
			st.addCell(gender);
			st.addCell(regdate);
			st.addCell(authority);
			st.addCell(photo);

		}
		wb.write();
		wb.close();
		out.close();
	}
}
