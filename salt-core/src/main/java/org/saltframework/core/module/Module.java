package org.saltframework.core.module;

import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 2.
 */
public class Module {
	private final String disable = "moduleId,moduleName,moduleIdx,skin,layoutIdx,browserTitle";

	private String moduleId;
	private String moduleName;
	private String moduleIdx;
	private Map<String, Object> options;

	public String getDisable() {
		return disable;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
		options.put("moduleId", moduleId);
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleIdx() {
		return moduleIdx;
	}

	public void setModuleIdx(String moduleIdx) {
		this.moduleIdx = moduleIdx;
	}
}
