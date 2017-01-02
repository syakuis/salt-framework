package egovmei.apps.modules.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 1. 2.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Portlet {

	private int height;
	private int width;
	private boolean isDraggable;
	private boolean isResizable;
	private boolean batchStatic;
	private int padding;
	private int coordX;
	private int coordY;
	private String portletId;
	private String portletName;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
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

	public boolean isBatchStatic() {
		return batchStatic;
	}

	public void setBatchStatic(boolean batchStatic) {
		this.batchStatic = batchStatic;
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

	public String getPortletId() {
		return portletId;
	}

	public void setPortletId(String portletId) {
		this.portletId = portletId;
	}

	public String getPortletName() {
		return portletName;
	}

	public void setPortletName(String portletName) {
		this.portletName = portletName;
	}

	@Override
	public String toString() {
		return "Portlet{" +
				"height=" + height +
				", width=" + width +
				", isDraggable=" + isDraggable +
				", isResizable=" + isResizable +
				", batchStatic=" + batchStatic +
				", padding=" + padding +
				", coordX=" + coordX +
				", coordY=" + coordY +
				", portletId='" + portletId + '\'' +
				", portletName='" + portletName + '\'' +
				'}';
	}
}
