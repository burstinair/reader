<head>
    <title>${author}</title>
</head>

<body>
    <ul>
		<@recent recentBookMark recentBookName />
        <#list books?if_exists as book>
            <li>
                <a href="/profile/${book.id}">${book.name}</a>
            </li>
        </#list>
    </ul>
    <div>
		<@pager "/author/${author}" />
        <a href="/">[返回首页]</a>
    </div>
</body>