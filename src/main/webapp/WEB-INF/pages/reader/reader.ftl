<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>
<@compress single_line=true>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title!"该书不存在。"}</title>
    </head>
    <body>
        <#if notExist>
			该书不存在。
        <#else>
            <div>
				${content}
            </div>
            <div>
            	<#assign pagerPrefix="/reader/${id}/${pageSize}">
                <#include "/WEB-INF/pages/pager.ftl">
            	<form action="/reader/gotoPage/${id}/${pageSize}" method="GET">
            		<input name="currentPage" value="${currentPage}" />
            		<input type="submit" value="GO" />
            	</form>
                <a href="${pagerPrefix}/${currentPage}/single">[设为标签]</a>
                <a href="${pagerPrefix}/${currentPage}/normal">[添加书签]</a>
            </div>
            <a href="/profile/${id}">[返回书页]</a>
        </#if>
        <a href="/">[返回首页]</a>
    </body>
</html>
</@compress>