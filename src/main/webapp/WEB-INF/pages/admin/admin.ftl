<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>
<@compress single_line=true>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理</title>
    </head>
    <body>
        <div>
            <a href="/">[返回首页]</a>
            <a href="/admin/executeSQL">[执行SQL]</a>
        </div>
        <div>
            <a href="/admin/edit">[添加新书]</a>
        </div>
        <table>
            <tr>
                <th>ID</th>
                <th>书名</th>
                <th>作者</th>
                <th>修改时间</th>
                <th>编辑</th>
                <th>可见</th>
            </tr>
            <#list books?if_exists as cur>
                <tr>
                    <td>${cur.id}</td>
                    <td>${cur.name}</td>
                    <td>${cur.author}</td>
                    <td>${cur.addDate?string("yyyy-MM-dd HH:mm:ss")}</td>
                    <td>
                        <a href="/admin/edit/${cur.id}">编辑</a>
                    </td>
                    <td>
                        <#if cur.visible == "visible">
                            <a href="/admin/${currentPage}/unvisible/${cur.id}">设为不可见</a>
                        <#else>
                            <a href="/admin/${currentPage}/visible/${cur.id}">设为可见</a>
                        </#if>
                    </td>
                </tr>
            </#list>
            <tr>
            	<td colspan="6">
		        	<#assign pagerPrefix="/admin">
		            <#include "/WEB-INF/pages/pager.ftl">
            	</td>
            </tr>
        </table>
    </body>
</html>
</@compress>