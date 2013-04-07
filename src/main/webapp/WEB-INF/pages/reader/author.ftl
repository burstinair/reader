<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>
<@compress single_line=true>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>${author}</title>
    </head>

    <body>
        <ul>
            <#include "/WEB-INF/pages/recent.ftl">
            <#list books?if_exists as book>
                <li>
                    <a href="/profile/${book.id}">${book.name}</a>
                </li>
            </#list>
        </ul>
        <div>
            <#assign pagerPrefix="/author/${author}">
		    <#include "/WEB-INF/pages/pager.ftl">
            <a href="/">[返回首页]</a>
        </div>
    </body>
</html>
</@compress>