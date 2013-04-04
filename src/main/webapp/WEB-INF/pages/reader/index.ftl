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
            <li>
                最近阅读：
                <a href="/reader/${recentBookMark.bookId}/${recentBookMark.wordCount}/${recentBookMark.page}">
                    [${recentBookName} | 第 ${recentBookMark.page} 页 | 每页字数 ${recentBookMark.wordCount}]
                </a>
            </li>
            <#list books?if_exists as book>
                <li>
                    [<a href="/author/${book.author}">${book.author}</a>]
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
