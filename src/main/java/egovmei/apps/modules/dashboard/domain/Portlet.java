package egovmei.apps.modules.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 1. 2.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Portlet {
	private String dashboardIdx;
	private String portletIdx;
	private String portletName;
	private String portletId;
	private int width;
	private int height;
	private int padding;
	private int coordX;
	private int coordY;
	private boolean isDraggable;
	private boolean isResizable;
	private boolean isStatic;

	public String getDashboardIdx() {
		return dashboardIdx;
	}

	public void setDashboardIdx(String dashboardIdx) {
		this.dashboardIdx = dashboardIdx;
	}

	public String getPortletIdx() {
		return portletIdx;
	}

	public void setPortletIdx(String portletIdx) {
		this.portletIdx = portletIdx;
	}

	public String getPortletName() {
		return portletName;
	}

	public void setPortletName(String portletName) {
		this.portletName = portletName;
	}

	public String getPortletId() {
		return portletId;
	}

	public void setPortletId(String portletId) {
		this.portletId = portletId;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public boolean isDraggable() {
		return isDraggable;
	}

	public void setDraggable(boolean draggable) {
		isDraggable = draggable;
	}

	public boolean isResizable() {
		return isResizable;
	}

	public void setResizable(boolean resizable) {
		isResizable = resizable;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean aStatic) {
		isStatic = aStatic;
	}
}
