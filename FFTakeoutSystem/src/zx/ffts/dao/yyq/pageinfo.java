package zx.ffts.dao.yyq;

public class pageinfo {
	private int nowpage;// ��ǰҳ
	private int star;// ��ʼ���
	private int end;// �������
	private int pagenum;// ÿҳ��ʾ������
	private int sumpage;// ��ҳ��
	private int sumnum;// ����Ϣ��

	public int getNowpage() {
		return nowpage;
	}

	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}

	public int getStar() {
		int st = (nowpage - 1) * pagenum;
		return st;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getEnd() {
		int en = nowpage * pagenum;
		return en;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getSumpage() {
		int sum = sumnum % pagenum > 0 ? sumnum / pagenum + 1 : sumnum
				/ pagenum;
		return sum;
	}

	public void setSumpage(int sumpage) {
		this.sumpage = sumpage;
	}

	public int getSumnum() {
		return sumnum;
	}

	public void setSumnum(int sumnum) {
		this.sumnum = sumnum;
	}

	public pageinfo() {
		super();

	}

	public pageinfo(int nowpage, int star, int end, int pagenum, int sumpage,
			int sumnum) {
		super();
		this.nowpage = nowpage;
		this.star = star;
		this.end = end;
		this.pagenum = pagenum;
		this.sumpage = sumpage;
		this.sumnum = sumnum;
	}
}
