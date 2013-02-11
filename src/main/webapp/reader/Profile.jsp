<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:property value="bookName" default="该书不存在。" /></title>
    </head>
    <body>
        <s:a href="/">[返回首页]</s:a>
        <s:if test="#action == null || notExist">
            该书不存在。
        </s:if>
        <s:else>
            <ul>
                <s:iterator value="bookMarks" id="mark">
                    <li>
                        <s:a href="%{@java.lang.String@format('/reader/reader/%d_%d_%d', #mark.bookId, #mark.page, #mark.wordCount)}">
                            [第 <s:property value="#mark.page" /> 页 | 每页字数 <s:property value="#mark.wordCount" />]
                        </s:a>
                    </li>
                </s:iterator>
                <li><s:a href="%{'/reader/reader/' + id}">[从开始阅读]</s:a></li>
            </ul>
        </s:else>
    </body>
</html>
