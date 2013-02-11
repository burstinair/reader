<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Reader</title>
    </head>

    <body>
    	<div>
            <s:a href="/admin/admin">[管理]</s:a>
        </div>
        <ul>
            <s:iterator value="books" id="cur">
                <li>
                    <s:a href="%{'/reader/profile/' + #cur.id}"><s:property value="#cur.name"/></s:a>
                </li>
            </s:iterator>
        </ul>
    </body>
</html>
