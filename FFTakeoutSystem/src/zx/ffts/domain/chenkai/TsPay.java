package zx.ffts.domain.chenkai;

import java.io.Serializable;

public class TsPay implements Serializable{
	private Integer pid;		//编号
	private String ptype;		//支付方式
	private Integer poid;		//对那张订单
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public Integer getPoid() {
		return poid;
	}
	public void setPoid(Integer poid) {
		this.poid = poid;
	}
	public TsPay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TsPay(Integer pid, String ptype, Integer poid) {
		super();
		this.pid = pid;
		this.ptype = ptype;
		this.poid = poid;
	}
	@Override
	public String toString() {
		return "TsPay [pid=" + pid + ", poid=" + poid + ", ptype=" + ptype
				+ "]";
	}
}
