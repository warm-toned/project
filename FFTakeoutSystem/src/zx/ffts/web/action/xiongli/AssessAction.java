package zx.ffts.web.action.xiongli;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import zx.ffts.dao.transaction.SetAssessTransaction;
import zx.ffts.dao.xiong.OrderFunctionDao;
import zx.ffts.domain.User;

public class AssessAction implements SessionAware {

	private String rtAssess;
	private List<String> menuAssess;
	private String uuid;
	private Integer score;
	private List<Integer> muScore;
	private Map<String, Object> session;
	private String rtid;
	private Double uscore;

	public Double getUscore() {
		return uscore;
	}

	public void setUscore(Double uscore) {
		this.uscore = uscore;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getRtid() {
		return rtid;
	}

	public void setRtid(String rtid) {
		this.rtid = rtid;
	}

	public String getRtAssess() {
		return rtAssess;
	}

	public void setRtAssess(String rtAssess) {
		this.rtAssess = rtAssess;
	}

	public List<String> getMenuAssess() {
		return menuAssess;
	}

	public void setMenuAssess(List<String> menuAssess) {
		this.menuAssess = menuAssess;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<Integer> getMuScore() {
		return muScore;
	}

	public void setMuScore(List<Integer> muScore) {
		this.muScore = muScore;
	}

	@Override
	public void setSession(Map<String, Object> sesMap) {
		session = sesMap;
	}

	public String setAssess() {
		Integer userid = ((User) session.get("user")).getUserid();
		new OrderFunctionDao().doTransaction(new SetAssessTransaction(), uuid,
				userid, rtid, rtAssess, score, muScore, menuAssess, uscore);
		return "success";
	}
}
