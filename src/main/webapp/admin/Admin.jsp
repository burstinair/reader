<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理</title>
    </head>
    <body>
        <div>
            <s:a href="/">[返回首页]</s:a>
        </div>
        <div>
            <s:a href="/admin/edit">[添加新书]</s:a>
        </div>
        <table>
            <tr>
                <th>ID</th>
                <th>书名</th>
                <th>编辑</th>
            </tr>
            <s:iterator value="books" id="cur">
                <tr>
                    <td><s:property value="#cur.id" /></td>
                    <td><s:property value="#cur.name" /></td>
                    <td>
                        <s:a href="%{'/admin/edit/' + #cur.id}">编辑</s:a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>
