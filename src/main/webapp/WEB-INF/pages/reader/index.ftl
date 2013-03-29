<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>

<!DOCTYPE html>
<html>
    <head>
        <title>Reader</title>
    </head>

    <body>
    	<div>
            <a href="/admin">[管理]</a>
        </div>
        <ul>
            <#list books?if_exists as book>
                <li>
                    <a href="/profile/${book.id}">${book.name}</a>
                </li>
            </#list>
        </ul>
        <div>
        	<#assign pagerPrefix="/">
        	<#include "/WEB-INF/pages/pager.ftl">
        </div>
    </body>
</html>
