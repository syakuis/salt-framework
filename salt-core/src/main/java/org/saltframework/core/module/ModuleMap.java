package org.saltframework.core.module;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public class ModuleMap implements Module, Serializable {
	private static final long serialVersionUID = -3417004497467828361L;

	private static final String GROUP_ID_FIELD = "groupId";
	private static final String MODULE_ID_FIELD = "moduleId";
	private static final String MODULE_NAME_FIELD = "moduleName";
	private static final String SKIN_FIELD = "skin";
	private static final String LAYOUT_ID_FIELD = "layoutId";
	private static final String PARENT_FIELD = "parent";

	private ModuleModel moduleModel;
	private Date create;

	public ModuleMap(Map<String, Object> map) {
		this(ModuleMap.load(map));
	}

	public ModuleMap(Properties properties) {
		this(ModuleMap.load(properties));
	}

	public ModuleMap(ModuleModel moduleModel) {
		this.moduleModel = moduleModel;
		this.create = new Date();

		Assert.notNull(moduleModel.getGroupId());
		Assert.notNull(moduleModel.getModuleId());
		Assert.notNull(moduleModel.getModuleName());
	}

	/**
	 * Properties to Map and ModuleModel
	 *
	 * @param properties Properties
	 * @return ModuleModel
	 */
	public static ModuleModel load(Properties properties) {
		Map<String, Object> map = new LinkedHashMap<>();

		for (final String name: properties.stringPropertyNames()) {
			map.put(name, properties.getProperty(name));
		}

		return ModuleMap.load(map);
	}

	/**
	 * Map 을 ModuleModel 로 바인딩
	 *
	 * @param map Map
	 * @return ModuleModel
	 */
	public static ModuleModel load(Map<String, Object> map) {
		String groupId = null;
		String moduleId = null;
		String moduleName = null;
		String skin = null;
		String layoutId = null;
		boolean parent = false;

		Map<String, Object> options = new LinkedHashMap<>();

		Iterator<String> names = map.keySet().iterator();
		while (names.hasNext()) {
			String name = names.next();
			String value = String.valueOf(map.get(name));

			if (name.equals(GROUP_ID_FIELD)) {
				groupId = value;
			} else if (name.equals(MODULE_ID_FIELD)) {
				moduleId = value;
			} else if (name.equals(MODULE_NAME_FIELD)) {
				moduleName = value;
			} else if (name.equals(SKIN_FIELD)) {
				skin = value;
			} else if (name.equals(LAYOUT_ID_FIELD)) {
				layoutId = value;
			} else if (name.equals(PARENT_FIELD)) {
				parent = Boolean.parseBoolean(value);
			} else {
				options.put(name, map.get(name));
			}
		}

		ModuleModel moduleModel;

		if (groupId == null || "".equals(groupId)) {
			moduleModel = new ModuleModel(moduleId, moduleName, options);
		} else {
			moduleModel = new ModuleModel(groupId, moduleId, moduleName, options);
		}

		moduleModel.setLayoutId(layoutId);
		moduleModel.setSkin(skin);
		moduleModel.setParent(parent);

		return moduleModel;
	}

	@Override
	public String getGroupId() {
		return moduleModel.getGroupId();
	}

	@Override
	public String getModuleId() {
		return moduleModel.getModuleId();
	}

	@Override
	public String getModuleName() {
		return moduleModel.getModuleName();
	}

	@Override
	public String getSkin() {
		return moduleModel.getSkin();
	}

	@Override
	public String getLayoutId() {
		return moduleModel.getLayoutId();
	}

	@Override
	public boolean isParent() {
		return moduleModel.isParent();
	}

	public Object getOption(String name) {
		return moduleModel.getOptions().get(name);
	}

	public Map<String, Object> toMap() {

		Map<String, Object> result = new LinkedHashMap<>(moduleModel.getOptions());

		result.put(GROUP_ID_FIELD, moduleModel.getGroupId());
		result.put(MODULE_ID_FIELD, moduleModel.getModuleId());
		result.put(MODULE_NAME_FIELD, moduleModel.getModuleName());
		result.put(SKIN_FIELD, moduleModel.getSkin());
		result.put(LAYOUT_ID_FIELD, moduleModel.getLayoutId());
		result.put(PARENT_FIELD, moduleModel.isParent());

		return result;
	}

	public List<Option> options() {
		Map<String, Object> options = moduleModel.getOptions();
		if (options.isEmpty()) {
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
		return "ModuleMap{" + super.toString() +
				", moduleModel=" + moduleModel +
				", create=" + create +
				'}';
	}
}
