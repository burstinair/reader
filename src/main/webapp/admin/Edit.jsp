<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:property default="Add Book" value="BookName" /></title>
    </head>
    <body>
        <div>
            <s:url action="../reader/Index" id="indexurl" />
            <s:a href="%{indexurl}">[返回首页]</s:a>
            <s:url action="Admin" id="adminurl" />
            <s:a href="%{adminurl}">[返回管理]</s:a>
        </div>
        <s:form method="post" enctype="multipart/form-data">
            <s:hidden type="hidden" name="id" value="%{#request.id}" />
            <s:textfield label="Book Name" name="name" value="%{book.name}" />
            <s:file name="upload" label="Select content file(*.txt)"></s:file>
            <s:submit label="Submit" />
        </s:form>
    </body>
</html>
