<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>
<@compress single_line=true>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${bookName!"该书不存在。"}</title>
    </head>
    <body>
        <a href="/">[返回首页]</a>
        <#if notExist>
			该书不存在。
        <#else>
            <ul>
                <#list bookMarks?if_exists as mark>
                    <li>
                        <a href="/reader/${mark.bookId}/${mark.wordCount}/${mark.page}">
                            [第 ${mark.page} 页 | 每页字数 ${mark.wordCount}
                            <#if mark.special == "auto">
                            	| 自动保存
                            <#elseif mark.special == "single">
                            	| 标签
                            </#if>
                            ]
                        </a>
                    </li>
                </#list>
                <li><a href="/reader/${id}">[从开始阅读]</a></li>
            </ul>
        </#if>
    </body>
</html>
</@compress>