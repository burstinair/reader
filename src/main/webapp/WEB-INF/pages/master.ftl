<#assign decorator = JspTaglibs["http://www.opensymphony.com/sitemesh/decorator"] />
<@decorator.usePage id="page" />
<@compress single_line = true>
<#assign is_mobile = userAgent?index_of("Mobile") != -1>
<#if (config.pageSchema)?if_exists == "strict" || is_mobile>
	<?xml version="1.0" encoding="utf-8"?>
	<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
<#else>
	<!DOCTYPE html>
	<html>
</#if>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><@decorator.title /></title>
        <#if is_mobile>
            <link href="/styles/mobile.css" rel="stylesheet" />
        </#if>
		<@decorator.head />
	</head>
	<body>
		<@decorator.body />
	</body>
</html>
</@compress>