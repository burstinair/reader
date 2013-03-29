<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><@s.property value="bookName" default="该书不存在。" /></title>
    </head>
    <body>
        <a href="/">[返回首页]</a>
        <#if notExist>
			该书不存在。
        <#else>
            <ul>
                <#list bookMarks?if_exists as mark>
                    <li>
                        <a href="/reader/${mark.bookId}/${mark.page}/${mark.wordCount}">
                            [第 ${mark.page} 页 | 每页字数 ${mark.wordCount}]
                        </a>
                    </li>
                </#list>
                <li><a href="/reader/${id}">[从开始阅读]</a></li>
            </ul>
        </#if>
    </body>
</html>