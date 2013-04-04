<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>
<@compress single_line=true>
<!DOCTYPE html>
<html>
    <head>
        <title>${author!"Reader"}</title>
    </head>

    <body>
    	<div>
            <a href="/admin">[管理]</a>
        </div>
        <ul>
            <#include "/WEB-INF/pages/recent.ftl">
            <#list books?if_exists.entrySet() as bookset>
                <li>
                    [<a href="/author/${bookset.key}">${bookset.key}</a>]
                    <#list bookset.value as book>
                        <br />
                        <a href="/profile/${book.id}">${book.name}</a>
                    </#list>
                </li>
            </#list>
        </ul>
        <div>
            <#assign pagerPrefix="">
		    <#include "/WEB-INF/pages/pager.ftl">
        </div>
    </body>
</html>
</@compress>