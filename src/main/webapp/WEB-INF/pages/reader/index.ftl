<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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