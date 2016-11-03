package egovmei.modules.demo.domain;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
public class Demo {
	private int demoIdx;
	private String userName;
	private String userId;
	private String memo;
	private int seq;

	public int getDemoIdx() {
		return demoIdx;
	}

	public void setDemoIdx(int demoIdx) {
		this.demoIdx = demoIdx;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
}
