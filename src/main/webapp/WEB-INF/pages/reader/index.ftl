<head>
    <title>${author!"Reader"}</title>
</head>

<body>
    <ul>
		<@recent recentBookMark recentBookName />
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
		<@pager "" />
        <a href="/admin">[管理]</a>
    </div>
</body>