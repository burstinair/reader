<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理</title>
    </head>
    <body>
        <div>
            <a href="/">[返回首页]</a>
        </div>
        <div>
            <a href="/admin/edit">[添加新书]</a>
        </div>
        <table>
            <tr>
                <th>ID</th>
                <th>书名</th>
                <th>编辑</th>
            </tr>
            <#list books?if_exists as cur>
                <tr>
                    <td>${cur.id}</td>
                    <td>${cur.name}</td>
                    <td>
                        <a href="/admin/edit/${cur.id}">编辑</a>
                    </td>
                </tr>
            </#list>
            <tr>
            	<td colspan="3">
		        	<#assign pagerPrefix="/admin">
		        	<#include "/WEB-INF/pages/pager.ftl">
            	</td>
            </tr>
        </table>
    </body>
</html>
