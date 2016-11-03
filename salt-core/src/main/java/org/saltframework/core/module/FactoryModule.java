package org.saltframework.core.module;

import java.io.Serializable;
import java.util.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public final class FactoryModule implements Module, Serializable {
	private static final long serialVersionUID = -3417004497467828361L;
	private final Map<String, Object> options;
	private Date create;

	public FactoryModule(Properties properties) {
		Map<String, Object> options = new HashMap<>();
		for (final String name: properties.stringPropertyNames()) {
			options.put(name, properties.getProperty(name));
		}

		this.options = options;
		this.create = new Date();
	}

	public FactoryModule(Map<String, Object> options) {
		this.options = new HashMap<>(options);
		this.create = new Date();
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

	public Map<String, Object> getOptions() {
		return new HashMap<>(this.options);
	}

	public boolean empty() {
		return options.isEmpty();
	}

	public boolean exists(String name) {
		return options.containsKey(name);
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

	@Override
	public String toString() {
		return "FactoryModule{" + super.toString() +
				"options=" + options +
				", create=" + create +
				'}';
	}
}
