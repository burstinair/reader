<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><@s.property default="添加新书" value="'编辑 ' + book.name" /></title>
    </head>
    <body>
        <div>
            <a href="/">[返回首页]</a>
            <a href="/admin">[返回管理]</a>
        </div>
        <@s.form method="post" action="/admin/editSubmit" enctype="multipart/form-data">
            <@s.hidden name="id" value="%{book.id}" />
            <@s.textfield label="Book Name" name="name" value="%{book.name}" />
            <@s.file name="upload" label="Select content file(*.txt)"></@s.file>
            <@s.submit label="Submit" />
        </@s.form>
    </body>
</html>