package org.saltframework.core.module;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http ://syaku.tistory.com
 * @since 2016. 11. 7.
 */
public final class ModuleModel implements Serializable {
	private static final long serialVersionUID = 6426236474133190177L;

	private final String groupId;
	private final String moduleId;
	private final String moduleName;
	private String skin;
	private String layoutId;
	private boolean parent;

	private final Map<String, Object> options;

	public ModuleModel(String moduleId, String moduleName) {
		this(moduleId, moduleId, moduleName, new LinkedHashMap());
	}

	public ModuleModel(String groupId, String moduleId, String moduleName) {
		this(moduleId, moduleId, moduleName, new LinkedHashMap());
	}

	public ModuleModel(String moduleId, String moduleName, Map<String, Object> options) {
		this(moduleId, moduleId, moduleName, options);
	}

	public ModuleModel(String groupId, String moduleId, String moduleName, Map<String, Object> options) {
		Assert.notNull(groupId);
		Assert.notNull(moduleId);
		Assert.notNull(moduleName);
		Assert.notNull(options);

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
