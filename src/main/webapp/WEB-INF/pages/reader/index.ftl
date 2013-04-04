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
            <#if author??>
                <a href="/">[返回首页]</a>
            </#if>
        </div>
        <ul>
            <#if !author??>
                <li>
                    最近阅读：
                    <a href="/reader/${recentBookMark.bookId}/${recentBookMark.wordCount}/${recentBookMark.page}">
                        [${recentBookName} | 第 ${recentBookMark.page} 页 | 每页字数 ${recentBookMark.wordCount}]
                    </a>
                </li>
            </#if>
            <#list books?if_exists.entrySet() as bookset>
                <li>
                    <#if !author??>
                        [<a href="/author/${bookset.key}">${bookset.key}</a>]
                        <br />
                    </#if>
                    <#list bookset.value as book>
                        <a href="/profile/${book.id}">${book.name}</a>
                        <br />
                    </#list>
                </li>
            </#list>
        </ul>
        <div>
            <#if author??>
                <#assign pagerPrefix="/author/${author}">
            <#else>
                <#assign pagerPrefix="">
            </#if>
        	<#include "/WEB-INF/pages/pager.ftl">
        </div>
    </body>
</html>
</@compress>