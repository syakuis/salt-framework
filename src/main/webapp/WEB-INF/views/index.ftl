<#assign RESOURCES_PATH><@spring.url '/resources' /></#assign>
<#assign browserCacheValue = .now?string("yyyyMMddHHmmss")>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=10">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!--[if lt IE 9]>
	<script type="text/javascript" src="${RESOURCES_PATH}/assets/dist/ie9.assets.min.js?browserCacheValue=${browserCacheValue}"></script>
	<![endif]-->

	<link rel="stylesheet" href="${RESOURCES_PATH}/assets/dist/assets.min.css?browserCacheValue=${browserCacheValue}">
	<link rel="stylesheet" href="${RESOURCES_PATH}/assets/dist/bootstrap.assets.min.css?browserCacheValue=${browserCacheValue}">
	<script type="text/javascript" src="${RESOURCES_PATH}/assets/dist/assets.min.js?browserCacheValue=${browserCacheValue}"></script>
</head>
<body>
	<#include template>
</body>
</html>