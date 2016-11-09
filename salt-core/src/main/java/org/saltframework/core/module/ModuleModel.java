package org.saltframework.core.module;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http ://syaku.tistory.com
 * @since 2016. 11. 7.
 */
public final class ModuleModel {
	private final String groupId;
	private final String moduleId;
	private final String moduleName;
	private String skin;
	private String layoutId;
	private boolean parent;

	private final Map<String, Object> options;

	public ModuleModel(String moduleId, String moduleName, Map<String, Object> options) {
		this(moduleId, moduleId, moduleName, options);
	}

	public ModuleModel(String groupId, String moduleId, String moduleName, Map<String, Object> options) {
		this.groupId = groupId;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.options = new LinkedHashMap<>(options);
	}

	public String getGroupId() {
		return groupId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

	public Map<String, Object> getOptions() {
		return options;
	}

	@Override
	public String toString() {
		return "ModuleModel{" + super.toString() +
				", groupId='" + groupId + '\'' +
				", moduleId='" + moduleId + '\'' +
				", moduleName='" + moduleName + '\'' +
				", skin='" + skin + '\'' +
				", layoutId='" + layoutId + '\'' +
				", parent=" + parent +
				", options=" + options +
				'}';
	}
}
