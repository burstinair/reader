<#assign decorator = JspTaglibs["http://www.opensymphony.com/sitemesh/decorator"] />
<@decorator.usePage id="page" />
<@compress single_line=true>
<#if config.pageSchema?if_exists == "strict">
	<?xml version="1.0" encoding="utf-8"?>
	<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
<#else>
	<!DOCTYPE html>
	<html>
</#if>
	<head>
		<title><@decorator.title /></title>
		<@decorator.head />
	</head>
	<body>
		<@decorator.body />
	</body>
</html>
</@compress>