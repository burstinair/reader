<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administration</title>
    </head>
    <body>
        <div>
            <s:url action="../reader/Index" id="indexurl" />
            <s:a href="%{indexurl}">[������ҳ]</s:a>
        </div>
        <div>
            <s:url action="Edit" id="editurl" />
            <s:a href="%{editurl}">[�������]</s:a>
        </div>
        <table>
            <tr>
                <th>ID</th>
                <th>����</th>
                <th>�༭</th>
            </tr>
            <s:iterator value="books" id="cur">
                <tr>
                    <td><s:property value="#cur.id" /></td>
                    <td><s:property value="#cur.name" /></td>
                    <td>
                        <s:url id="url" action="Edit">
                            <s:param name="id"><s:property value="#cur.id"/></s:param>
                        </s:url>
                        <s:a href="%{url}">�༭</s:a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>
