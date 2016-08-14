package zx.ffts.domain.chenshun;

import java.io.File;
import java.io.Serializable;

import javax.servlet.ServletContext;

/**
 * 上传类
 * @author Administrator
 *
 */
public class up implements Serializable  { //上传必须实现serializable
	protected  File abc;
    protected String abcContentType;
    protected String abcFileName;
    protected ServletContext application;
	public File getAbc() {
		return abc;
	}
	public void setAbc(File abc) {
		this.abc = abc;
	}
	public String getAbcContentType() {
		return abcContentType;
	}
	public void setAbcContentType(String abcContentType) {
		this.abcContentType = abcContentType;
	}
	public String getAbcFileName() {
		return abcFileName;
	}
	public void setAbcFileName(String abcFileName) {
		this.abcFileName = abcFileName;
	}
	public ServletContext getApplication() {
		return application;
	}
	public void setApplication(ServletContext application) {
		this.application = application;
	}
	public up(File abc, String abcContentType, String abcFileName,
			ServletContext application) {
		super();
		this.abc = abc;
		this.abcContentType = abcContentType;
		this.abcFileName = abcFileName;
		this.application = application;
	}
	public up() {
		super();
		// TODO Auto-generated constructor stub
	}
	

    
}
