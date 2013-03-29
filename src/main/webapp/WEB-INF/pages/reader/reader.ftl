<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><@s.property value="title" default="该书不存在。" /></title>
    </head>
    <body>
        <#if notExist>
			该书不存在。
        <#else>
            <div class="content">
            	${title}
                ${content}
            </div>
            <div class="navi">
            	<#assign pagerPrefix="/reader/${id}/${pageSize}">
            	<#include "/WEB-INF/pages/pager.ftl">
                <br />
            	<form action="/reader/gotoPage/${id}/${pageSize}" method="GET">
            		<input name="currentPage" value="${currentPage}" />
            		<input type="submit" value="GO" />
            	</form>
                <br />
                <a href="${pagerPrefix}/${currentPage}/single">[设为标签]</a>
                <a href="${pagerPrefix}/${currentPage}/normal">[添加书签]</a>
            </div>
            <a href="/profile/${id}">[返回书页]</a>
        </#if>
        <a href="/">[返回首页]</a>
    </body>
</html>
