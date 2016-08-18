package zx.ffts.web.action.pain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import zx.ffts.dao.pain.RestaurantDao;
import zx.ffts.utils.DbUtils;

public class OpenShopAction implements ServletRequestAware {

	private HttpServletRequest request;
	private String rtname;
	private String rtaddr;
	private String rtcontent;
	private File rtpic;
	private String rtpicFileName;
	private String rtonbuz;
	private Integer userid;
	private RestaurantDao resDao = new RestaurantDao();

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getRtname() {
		return rtname;
	}

	public void setRtname(String rtname) {
		this.rtname = rtname;
	}

	public String getRtaddr() {
		return rtaddr;
	}

	public void setRtaddr(String rtaddr) {
		this.rtaddr = rtaddr;
	}

	public String getRtcontent() {
		return rtcontent;
	}

	public void setRtcontent(String rtcontent) {
		this.rtcontent = rtcontent;
	}

	public File getRtpic() {
		return rtpic;
	}

	public void setRtpic(File rtpic) {
		this.rtpic = rtpic;
	}

	public String getRtpicFileName() {
		return rtpicFileName;
	}

	public void setRtpicFileName(String rtpicFileName) {
		this.rtpicFileName = rtpicFileName;
	}

	public String getRtonbuz() {
		return rtonbuz;
	}

	public void setRtonbuz(String rtonbuz) {
		this.rtonbuz = rtonbuz;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String setApply() throws IOException {
		String path = "image/restaurant";
		String name = rtpicFileName;// 文件名
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/image/restaurant");// 目录真实路径
		int code = rtpicFileName.hashCode();
		String hex = Integer.toHexString(code);
		realPath += "\\" + hex.charAt(0) + "\\" + hex.charAt(1);
		File paths = new File(realPath);
		paths.mkdirs();
		name = DbUtils.getUUID() + name.substring(name.lastIndexOf("."));
		path += "/" + hex.charAt(0) + "/" + hex.charAt(1) + "/" + name;
		File file = new File(realPath, name);
		FileInputStream fi = new FileInputStream(rtpic);
		FileOutputStream fo = new FileOutputStream(file);
		byte[] data = new byte[1024];
		int len = 0;
		while ((len = fi.read(data)) != -1) {
			fo.write(data, 0, len);
			fo.flush();
		}
		fi.close();
		fo.close();
		int line = resDao.setApply(rtname, rtaddr, userid, path, rtcontent,
				rtonbuz);
		if (line > 0) {
			request.getSession().setAttribute("os", true);
		}
		return "success";
	}
}
