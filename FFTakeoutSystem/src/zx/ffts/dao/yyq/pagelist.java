package zx.ffts.dao.yyq;

import java.util.List;

public class pagelist {
	private pageinfo info;
	private List list;

	public pageinfo getInfo() {
		return info;
	}

	public void setInfo(pageinfo info) {
		this.info = info;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public pagelist() {
		super();

	}

	public pagelist(pageinfo info, List list) {
		super();
		this.info = info;
		this.list = list;
	}

}
