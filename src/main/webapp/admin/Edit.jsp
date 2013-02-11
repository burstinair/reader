<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:property default="添加新书" value="'编辑 ' + book.name" /></title>
    </head>
    <body>
        <div>
            <s:a href="/">[返回首页]</s:a>
            <s:a href="/admin/admin">[返回管理]</s:a>
        </div>
        <s:form method="post" action="%{'edit/' + #parameters.id}" enctype="multipart/form-data">
            <s:hidden name="_method" value="put" />
            <s:textfield label="Book Name" name="name" value="%{book.name}" />
            <s:file name="upload" label="Select content file(*.txt)"></s:file>
            <s:submit label="Submit" />
        </s:form>
    </body>
</html>
