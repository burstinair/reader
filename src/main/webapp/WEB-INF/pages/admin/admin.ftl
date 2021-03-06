<head>
    <title>管理</title>
</head>
<body>
    <div>
        <a href="/">[返回首页]</a>
        <a href="/admin/executeSQL">[执行SQL]</a>
    </div>
    <div>
        <a href="/admin/edit">[添加新书]</a>
        <a href="/admin/addMult">[批量添加]</a>
    </div>
    <table>
        <tr>
            <th>ID</th>
            <th>书名</th>
            <th>作者</th>
            <th>修改时间</th>
            <th>编辑</th>
            <th>可见</th>
        </tr>
        <#list books?if_exists as cur>
            <tr>
                <td>${cur.id}</td>
                <td>${cur.name}</td>
                <td>${cur.author}</td>
                <td>${cur.addDate?string("yyyy-MM-dd HH:mm:ss")}</td>
                <td>
                    <a href="/admin/edit/${cur.id}">编辑</a>
                </td>
                <td>
                    <#if cur.visible == "visible">
                        <a href="/admin/${currentPage}/unvisible/${cur.id}">设为不可见</a>
                    <#else>
                        <a href="/admin/${currentPage}/visible/${cur.id}">设为可见</a>
                    </#if>
                </td>
            </tr>
        </#list>
        <tr>
        	<td colspan="6">
				<@pager "/admin" />
        	</td>
        </tr>
    </table>
</body>