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
                    [<a href="/author/${bookset.key}">${bookset.key}</a>]
                    <ul>
                        <#list bookset.value as book>
                            <a href="/profile/${book.id}">${book.name}</a>
                        </#list>
                    </ul>
                </li>
            </#list>
        </ul>
        <div>
        	<#assign pagerPrefix="/">
        	<#include "/WEB-INF/pages/pager.ftl">
        </div>
    </body>
</html>
