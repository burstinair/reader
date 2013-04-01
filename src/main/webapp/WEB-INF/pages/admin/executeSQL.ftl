<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>
<!DOCTYPE html>
<html>
	<head>
		<title>execute sql query</title>
	</head>
	<body>
		<form method="POST">
			SQL:
			<br />
			<input name="queryString" />
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