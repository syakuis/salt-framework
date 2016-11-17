package org.saltframework.core.module;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public class ModuleMap implements Module, Serializable {

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
		Assert.notNull(moduleModel.getGroupId());
		Assert.notNull(moduleModel.getModuleId());
		Assert.notNull(moduleModel.getModuleName());

		this.moduleModel = moduleModel;
		this.create = new Date();

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
	 * null 인 경우 대입되지 않음. 그래서 기존에 값을 유지할 필요가 있음.
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

		for(Map.Entry<String, Object> item : map.entrySet()) {
			String name = item.getKey();
			String value = (item.getValue() == null) ? null : String.valueOf(item.getValue());

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
				options.put(name, value);
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
		for(Map.Entry<String, Object> option : options.entrySet()) {
			moduleOptions.add(new Option(option.getKey(), option.getValue()));
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
