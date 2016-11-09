<div class="page-header">
	<h1>Module</h1>
</div>

<div class="alert alert-warning" role="alert">
	<p>스킨 선택과 옵션 파일은 모듈을 생성 후 활용할 수 있습니다.</p>
</div>


<form id="form">
<input type="hidden" name="module_idx" value="${module.moduleIdx?if_exists?html}">
	
	<div class="form-horizontal">
	
		<p class="lead">기본 설정</p>
		<div class="form-group">
			<label for="module_name" class="col-sm-3 control-label">모듈</label>
			<div class="col-sm-9">
			<#if module.moduleName?exists>
			<p class="form-control-static">${module.moduleName}</p>
			<#else>
			<select class="form-control" id="module_name" name="module_name" required>
			<option>모듈 선택</option>
			<#list listModulesFolder?if_exists as module>
			<option value="${module}">${module}</option>
			</#list>
			</select>
			</#if>
			</div>
		</div>
		
		<div class="form-group">
			<label for="module_id" class="col-sm-3 control-label">모듈 ID</label>
			<div class="col-sm-9">
			<#if module.moduleId?exists>
			<p class="form-control-static">${module.moduleId}</p>
			<#else>
			<input type="text" class="form-control" id="module_id" name="module_id" maxlength="150" required>
			<p class="help-block">모듈 아이디을 입력하세요. 첫글자 영소문자 _ 숫자 조합만 사용할 수 있습니다.</p>
			</#if>
			</div>
		</div>
		
		<div class="form-group">
			<label for="browser_title" class="col-sm-3 control-label">브라우저 타이틀</label>
			<div class="col-sm-9">
			<input type="text" class="form-control" id="browser_title" name="browser_title" maxlength="255" required value="${module.browserTitle?if_exists?html}">
			</div>
		</div>

		<div class="form-group">
			<label for="use_theme" class="col-sm-3 control-label">테마 사용</label>
			<div class="col-sm-9">
				<div class="checkbox">
					<label>
						<input type="checkbox" id="use_theme" name="use_theme" value="1"> 테마를 사용합니다.
					</label>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label for="layout_idx" class="col-sm-3 control-label">레이아웃</label>
			<div class="col-sm-9">
			<select class="form-control" id="layout_idx" name="layout_idx">
			<option value="">레이아웃 선택</option>
			<#list listLayout?if_exists as layout>
			<option value="${layout.layoutIdx}">${layout.title}</option>
			</#list>
			</select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="skin" class="col-sm-3 control-label">스킨</label>
			<div class="col-sm-9">
			<select class="form-control" id="skin" name="skin">
			<option value="">스킨 선택</option>
			<#list listSkinsFolder?if_exists as skin>
			<option value="${skin}">${skin}</option>
			</#list>
			</select>
			</div>
		</div>

		
		<div class="form-group">
			<label for="options_file" class="col-sm-3 control-label">기본 옵션 사용여부</label>
			<div class="col-sm-9">
				<label class="radio-inline">
					<input type="radio" name="is_options" id="is_options1" value="N" checked> 아니오
				</label>
				<label class="radio-inline">
					<input type="radio" name="is_options" id="is_options2" value="Y"> 예
				</label>
				<p class="help-block">하나의 모듈을 여러 개의 모듈로 관리할 때 사용하며, 1개 모듈만 기본 옵션으로 사용할 수 있습니다.</p>
			</div>
		</div>
		
		<div class="form-group">
			<label for="options_file" class="col-sm-3 control-label">확장 여부</label>
			<div class="col-sm-9">
				<label class="radio-inline">
					<input type="radio" name="is_extend" value="N" checked> 아니오
				</label>
				<label class="radio-inline">
					<input type="radio" name="is_extend" value="Y"> 예
				</label>
				<p class="help-block">현재 모듈이 여러개로 확장되는 방식인 경우 예를 선택하세요.</p>
			</div>
		</div>
		
		<div class="form-group">
			<label for="options_file" class="col-sm-3 control-label">옵션 파일</label>
			<div class="col-sm-9">
			<input type="text" class="form-control" id="options_file" name="options_file" value="${module.optionsFile?if_exists?html}">
			<p class="help-block"><kbd>/src/main/webapp/ain/views</kbd> 경로를 생략한 다음 모든 경로를 / 시작하여 입력하세요.</p>
			</div>
		</div>
		<hr>
		
		<!-- 옵션 설정 파일 include -->
		<#assign is_options_not_file = true>
		<#attempt>
		<#if module.optionsFile??>
		<#assign is_options_not_file = false>
		<#include module.optionsFile>
		</#if>
		<#recover>
		<#assign is_options_not_file = true>
		</#attempt>
		<!-- 옵션 설정 파일 include -->
	</div><!-- form-horizontal -->
	
	<div><!-- 옵션 설정 -->
		<#if is_options_not_file>
		<p class="lead">옵션 설정 <button type="button" class="btn btn-success" onclick="fnAddItem();"><i class="fa fa-plus"></i> 추가</button></p>
		<div id="options_items">
		
		<#assign i = 0>
		<#list module.moduleOptions?if_exists as obj>
		<div class="row col_${i}">
			<div class="col-sm-1">
				<div class="form-group"><button type="button" class="btn btn-danger" onclick="fnDeleteItem(${i});"><i class="fa fa-times"></i></button></div>
			</div>
			<div class="col-sm-3">
				<div class="form-group"><input type="text" class="form-control" name="options[][name]" placeholder="옵션명" value="${obj.optionsName?if_exists?html}" required></div>
			</div>
			<div class="col-sm-4">
				<div class="form-group"><input type="text" class="form-control" name="options[][value]" placeholder="옵션값" value="${obj.optionsValue?if_exists?html}"></div>
			</div>
			<div class="col-sm-4">
				<div class="form-group"><input type="text" class="form-control" name="options[][comment]" placeholder="옵션설명" value="${obj.optionsComment?if_exists?html}"></div>
			</div>
		</div>
		<#assign i = i + 1>
		</#list>
		
		</div>
		
		
	<script id="template" type="text/x-handlebars-template">
	<div class="row col_{{index}}">
		<div class="col-sm-1">
			<div class="form-group"><button type="button" class="btn btn-danger" onclick="fnDeleteItem({{index}});"><i class="fa fa-times"></i></button></div>
		</div>
		<div class="col-sm-3">
			<div class="form-group"><input type="text" class="form-control" name="options[][name]" placeholder="옵션명" required></div>
		</div>
		<div class="col-sm-4">
			<div class="form-group"><input type="text" class="form-control" name="options[][value]" placeholder="옵션값"></div>
		</div>
		<div class="col-sm-4">
			<div class="form-group"><input type="text" class="form-control" name="options[][comment]" placeholder="옵션설명"></div>
		</div>
	</div>
	</script>
		
		<script type="text/javascript">
			var source = jQuery("#template").html(); 
			var template = Handlebars.compile(source);
			
			function fnAddItem() {
				var index = jQuery('#options_items .row').get().length;
				jQuery('#options_items').append(template({ index : index}));
			}
			
			function fnDeleteItem(index) {
				jQuery('#options_items .col_' + index).remove();
			}
		</script>
		</#if>
	</div><!-- 옵션 설정 -->
	
	<div class="text-center">
	<button type="submit" class="btn btn-success"><i class="fa fa-check"></i> 적용</button>
	<a class="btn btn-default" href="<@spring.url '/admin/module' />" role="button"><i class="fa fa-bars"></i> 목록</a>
	</div>

</form>

<script type="text/javascript">
jQuery("#form input:checkbox[name='use_theme']:checkbox[value='${module.useTheme?if_exists?js_string}']").prop("checked",true);
jQuery('#form #layout_idx').val('${module.layoutIdx?if_exists?js_string}');
jQuery('#form #skin').val('${module.skin?if_exists?js_string}');

jQuery("#form input:radio[name='is_options']:radio[value='${module.isOptions?if_exists?js_string}']").prop("checked",true);
jQuery("#form input:radio[name='is_extend']:radio[value='${module.isExtend?if_exists?js_string}']").prop("checked",true);

jQuery("#form").action3({
	url : "<@spring.url '/admin/module/save' />",
	afterSuccess: function(data) {
		<#if module.moduleIdx??>
		jQuery._notify.success("<@spring.message "text.success.save" />");
		<#else>
		location.href = "<@spring.url '/admin/module' />";
		</#if>
	},
	eachValid: true
});
</script>

