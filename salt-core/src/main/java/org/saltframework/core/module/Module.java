package org.saltframework.core.module;

import java.util.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 2.
 */
public final class Module {
	private Date create;
	private Map<String, Object> options;
	private Date update;

	public Module(Properties properties) {
		this.options = new HashMap<>((Map<String, Object>) properties.entrySet());
	}

	public Module(Map<String, Object> options) {
		this.options = new HashMap<>(options);
	}

	public Date getCreate() {
		return create;
	}

	public Date getUpdate() {
		return update;
	}

	public String getModuleId() {
		return (String) options.get(ModuleName.moduleId);
	}

	public String getModuleName() {
		return (String) options.get(ModuleName.moduleName);
	}

	public String getModuleIdx() {
		return (String) options.get(ModuleName.moduleIdx);
	}

	public Object getOption(String name) {
		return options.get(name);
	}

	public boolean empty() {
		return options.isEmpty();
	}

	public boolean exists(String name) {
		return options.containsKey(name);
	}

	public Options option() {
		return new Options(options);
	}

	public List<Option> options() {
		if (empty()) {
			return Collections.EMPTY_LIST;
		}

		List<Option> moduleOptions = new ArrayList<>();
		Iterator<String> names = options.keySet().iterator();
		while(names.hasNext()) {
			String name = names.next();
			moduleOptions.add(new Option(name, options.get(name)));
		}

		return moduleOptions;
	}
}
