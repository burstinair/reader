<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>
<@compress single_line=true>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>execute sql query</title>
	</head>
	<body>
		<form method="POST">
			SQL:
			<br />
			<input name="queryString" value="${queryString!''}" />
			<input type="submit" name="nonQuery" value="执行SQL" />
			<input type="submit" name="query" value="执行SQL并返回结果" />
		</form>
		
		<#if resultRows??>
			<table class="result">
				<#list resultRows?if_exists as row>
					<tr>
						<#list row as column>
							<td>${column}</td>
						</#list>
					</tr>
				</#list>
			</table>
		</#if>

	</body>
</html>
</@compress>