package zx.ffts.web.action.pain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import zx.ffts.dao.pain.UserDao;
import zx.ffts.domain.User;
import zx.ffts.utils.DbUtils;

public class PhotoAction implements ServletRequestAware, ServletContextAware {

	private HttpServletRequest request;
	private ServletContext application;
	private HttpSession session;
	private File photo;
	private String photoFileName;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	@Override
	public void setServletContext(ServletContext context) {
		application = context;
	}

	/**
	 * 上传照片
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public String upload() throws FileNotFoundException, IOException {
		String photoPath = "image/users";     //数据库的路径
		String name = photoFileName;     //文件名称
		int code = photoFileName.hashCode();   //获取当前文件的hashCode
		String hex = Integer.toHexString(code);   //转为字符串
		String realPath = application.getRealPath("/image/user");  //文件的路径
		realPath += "\\" + hex.charAt(0) + "\\" + hex.charAt(1);     //获取hashcode第一位和第二位字符
		File path = new File(realPath);    // 创建一个新路径
		path.mkdirs();   //创建路径
		name = DbUtils.getUUID() + name.substring(name.lastIndexOf("."));  // uuid+文件名称         
		photoPath += "/" + hex.charAt(0) + "/" + hex.charAt(1) + "/" + name;   // 数据库路径+hashcode第一位字符+hashcode第二位字符 +文件名称
		File file = new File(realPath, name);            //  在新目录下创建目录名称
		FileInputStream fi = new FileInputStream(photo);     //获取该文件
		FileOutputStream fo = new FileOutputStream(file);       //写入到新的路径
		byte[] data = new byte[1024];        //写入
		int len = 0;
		while ((len = fi.read(data)) != -1) {
			fo.write(data, 0, len);
			fo.flush();
		}
		fi.close();
		fo.close();
		User user = (User) session.getAttribute("user");
		Integer line = new UserDao().editPhoto(photoPath, user.getUserid());
		if (line > 0) {
			user.setPhoto(photoPath);
		}
		return "back";
	}

	public String regist() throws IOException {
		UserDao userdao = new UserDao();
		String photoPath = null;
		if (photo != null) {
			photoPath = "image/users";
			String name = photoFileName;
			int code = photoFileName.hashCode();
			String hex = Integer.toHexString(code);
			String realPath = application.getRealPath("/image/users");
			realPath += "\\" + hex.charAt(0) + "\\" + hex.charAt(1);
			File path = new File(realPath);
			path.mkdirs();
			name = DbUtils.getUUID() + name.substring(name.lastIndexOf("."));
			photoPath += "/" + hex.charAt(0) + "/" + hex.charAt(1) + "/" + name;
			File file = new File(realPath, name);
			FileInputStream fi = new FileInputStream(photo);
			FileOutputStream fo = new FileOutputStream(file);
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = fi.read(data)) != -1) {
				fo.write(data, 0, len);
				fo.flush();
			}
			fi.close();
			fo.close();
		} else {
			photoPath = "image/defaults/userdefault.jpg";
		}
		Integer id = userdao.regist(user.getUsername(), user.getPwd(),
				user.getTel(), user.getAddress(), user.getRealname(),
				user.getGender(), photoPath);
		if (id != -1) {
			session.setAttribute("user", userdao.getUserById(id));
			return "registSuccess";
		} else {
			return "fail";
		}
	}
}
