package egovmei.apps.modules.dashboard.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 1. 2.
 */
public class DashboardVO {
	private Dashboard dashboard;
	private List<Portlet> portlets;
	private Map<String, Object> layouts;
	private List<Object> layout;

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public List<Portlet> getPortlets() {
		return portlets;
	}

	public void setPortlets(List<Portlet> portlets) {
		this.portlets = portlets;
	}

	public Map<String, Object> getLayouts() {
		return layouts;
	}

	public void setLayouts(Map<String, Object> layouts) {
		this.layouts = layouts;
	}

	public List<Object> getLayout() {
		return layout;
	}

	public void setLayout(List<Object> layout) {
		this.layout = layout;
	}

	@Override
	public String toString() {
		return "DashboardVO{" +
				"dashboard=" + dashboard +
				", portlets=" + portlets +
				", layouts='" + layouts + '\'' +
				", layout='" + layout + '\'' +
				'}';
	}
}
