package org.saltframework.core.module;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 2.
 */
public final class Module {
	private final String moduleId;
	private final String moduleName;
	private String moduleIdx;
	private ModuleOption options;

	public Module(String moduleId, String moduleName) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getModuleIdx() {
		return moduleIdx;
	}

	public void setModuleIdx(String moduleIdx) {
		this.moduleIdx = moduleIdx;
	}

	public ModuleOption getOptions() {
		return options;
	}

	public void setOptions(ModuleOption options) {
		this.options = options;
	}
}
