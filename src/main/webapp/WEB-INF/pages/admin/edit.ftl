<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>
<head>
    <title>${("编辑" + book.name)!"添加新书"}</title>
</head>
<body>
    <div>
        <a href="/">[返回首页]</a>
        <a href="/admin">[返回管理]</a>
    </div>
    <@s.form method="post" action="/admin/editSubmit" enctype="multipart/form-data">
        <@s.hidden name="id" value="%{book.id}" />
        <@s.textfield label="书名" name="name" value="%{book.name}" />
        <@s.textfield label="作者" name="author" value="%{book.author}" />
        <@s.file name="upload" label="选择文件(*.txt)"></@s.file>
        <@s.submit label="提交" />
    </@s.form>
</body>