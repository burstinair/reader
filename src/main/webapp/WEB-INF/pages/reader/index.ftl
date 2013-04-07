<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>
<@compress single_line=true>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>${author!"Reader"}</title>
    </head>

    <body>
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
            <a href="/admin">[管理]</a>
        </div>
    </body>
</html>
</@compress>