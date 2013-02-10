<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:property value="title" default="该书不存在。" /></title>
    </head>
    <body>
        <s:if test="#action == null">
            该书不存在。
        </s:if>
        <s:elseif test="notExist">
            该书不存在。
        </s:elseif>
        <s:else>
        	<s:url action="Index" id="homepage"></s:url>
        	<s:a href="%{homepage}">[返回首页]</s:a>
        	<s:url action="Profile" id="profile">
        		<s:param name="id" value="#parameters.id"></s:param>
        	</s:url>
        	<s:a href="%{profile}">[返回书页]</s:a>
            <div class="content">
                <s:property escape="false" value="content" />
            </div>
            <div class="navi">
                <s:if test="isFirstPage != true">
                    <s:url action="Reader" id="firstpageurl">
                        <s:param name="id" value="#parameters.id"></s:param>
                        <s:param name="cp" value="1"></s:param>
                        <s:param name="wc" value="wordCount"></s:param>
                    </s:url>
                    <s:a href="%{firstpageurl}">[第一页]</s:a>
                    <s:url action="Reader" id="prevpageurl">
                        <s:param name="id" value="#parameters.id"></s:param>
                        <s:param name="cp" value="curPage - 1"></s:param>
                        <s:param name="wc" value="wordCount"></s:param>
                    </s:url>
                    <s:a href="%{prevpageurl}">[上页]</s:a>
                </s:if>
                <s:if test="isLastPage != true">
                    <s:url action="Reader" id="nextpageurl">
                        <s:param name="id" value="#parameters.id"></s:param>
                        <s:param name="cp" value="curPage + 1"></s:param>
                        <s:param name="wc" value="wordCount"></s:param>
                    </s:url>
                    [<s:a href="%{nextpageurl}">下页</s:a>]
                    <s:url action="Reader" id="lastpageurl">
                        <s:param name="id" value="#parameters.id"></s:param>
                        <s:param name="cp" value="pageCount"></s:param>
                        <s:param name="wc" value="wordCount"></s:param>
                    </s:url>
                    <s:a href="%{lastpageurl}">[末页]</s:a>
                </s:if>
                <br />
                <s:url action="Reader" id="addbookmark">
                    <s:param name="id" value="#parameters.id"></s:param>
                    <s:param name="cp" value="1"></s:param>
                    <s:param name="wc" value="wordCount"></s:param>
                    <s:param name="action" value="'bookmark'"></s:param>
                </s:url>
                <s:a href="%{addbookmark}">[添加书签]</s:a>
            </div>
        </s:else>
    </body>
</html>
