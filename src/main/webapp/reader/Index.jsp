<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Reader</title>
    </head>

    <body>
    	<div>
            <s:url action="../admin/Admin" id="adminurl" />
            <s:a href="%{adminurl}">[Go to Admin]</s:a>
        </div>
        <ul>
            <s:iterator value="books" id="cur">
                <li>
                    <s:url id="url" action="Profile">
                        <s:param name="id"><s:property value="#cur.id"/></s:param>
                    </s:url>
                    <s:a href="%{url}"><s:property value="#cur.name"/></s:a>
                </li>
            </s:iterator>
        </ul>
    </body>
</html>

