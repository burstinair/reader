<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${title!"该书不存在。"}</title>
</head>
<body>
    <#if notExist>
		该书不存在。
    <#else>
        <div>
			${content}
        </div>
        <div>
        	<#assign prefix="/reader/${id}/${pageSize}" />
        	<@pager prefix />
        	<form action="/reader/gotoPage/${id}/${pageSize}" method="GET">
        		<input name="currentPage" value="${currentPage}" />
        		<input type="submit" value="GO" />
        	</form>
            <a href="${prefix}/${currentPage}/single">[设为标签]</a>
            <a href="${prefix}/${currentPage}/normal">[添加书签]</a>
        </div>
        <a href="/profile/${id}">[返回书页]</a>
    </#if>
    <a href="/">[返回首页]</a>
</body>