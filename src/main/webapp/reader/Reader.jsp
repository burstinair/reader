<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:property value="title" default="该书不存在。" /></title>
    </head>
    <body>
        <s:if test="#action == null || notExist">
            该书不存在。
        </s:if>
        <s:else>
            <div class="content">
                <s:property escape="false" value="content" />
            </div>
            <div class="navi">
                <s:if test="isLastPage != true">
                    [<s:a href="%{@java.lang.String@format('/reader/reader/%d_%d_%d', parsedId, curPage + 1, wordCount)}">下一页</s:a>]
                </s:if>
                <s:if test="isFirstPage != true">
                    [<s:a href="%{@java.lang.String@format('/reader/reader/%d_%d_%d', parsedId, curPage - 1, wordCount)}">上一页</s:a>]
                </s:if>
                [<s:a href="%{@java.lang.String@format('/reader/reader/%d_1_%d', parsedId, wordCount)}">第一页</s:a>]
                [<s:a href="%{@java.lang.String@format('/reader/reader/%d_%d_%d', parsedId, pageCount, wordCount)}">末页</s:a>]
                <br />
                <s:a href="%{@java.lang.String@format('/reader/reader/%d_%d_%d_abm', parsedId, curPage, wordCount)}">[添加书签]</s:a>
            </div>
            <s:a href="%{'/reader/profile/' + parsedId}">[返回书页]</s:a>
        </s:else>
        <s:a href="/">[返回首页]</s:a>
    </body>
</html>
