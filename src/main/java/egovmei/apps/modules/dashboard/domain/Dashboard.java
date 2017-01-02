package egovmei.apps.modules.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dashboard {
	private String dashboardIdx;
	private String title;
	private int marginX;
	private int marginY;
	private int paddingX;
	private int paddingY;
	private int height;

	public String getDashboardIdx() {
		return dashboardIdx;
	}

	public void setDashboardIdx(String dashboardIdx) {
		this.dashboardIdx = dashboardIdx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMarginX() {
		return marginX;
	}

	public void setMarginX(int marginX) {
		this.marginX = marginX;
	}

	public int getMarginY() {
		return marginY;
	}

	public void setMarginY(int marginY) {
		this.marginY = marginY;
	}

	public int getPaddingX() {
		return paddingX;
	}

	public void setPaddingX(int paddingX) {
		this.paddingX = paddingX;
	}

	public int getPaddingY() {
		return paddingY;
	}

	public void setPaddingY(int paddingY) {
		this.paddingY = paddingY;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Dashboard{" +
				"dashboardIdx='" + dashboardIdx + '\'' +
				", title='" + title + '\'' +
				", marginX=" + marginX +
				", marginY=" + marginY +
				", paddingX=" + paddingX +
				", paddingY=" + paddingY +
				", height=" + height +
				'}';
	}
}
