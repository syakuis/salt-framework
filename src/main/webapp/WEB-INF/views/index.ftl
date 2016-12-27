<#assign RESOURCES_PATH><@spring.url '/resources' /></#assign>
<#assign browserCacheValue = .now?string("yyyyMMddHHmmss")>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=10">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="${RESOURCES_PATH}/dist/css/assets.min.css?browserCacheValue=${browserCacheValue}">
	<script src="${RESOURCES_PATH}/dist/js/assets.min.js?browserCacheValue=${browserCacheValue}" /></script>
</head>
<body>
	<#include template>
</body>
</html>