<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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