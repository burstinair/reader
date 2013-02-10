<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:property value="title" default="The book does not exist." /></title>
    </head>
    <body>
        <s:if test="#action == null">
            This book does not exist.
        </s:if>
        <s:elseif test="notExist">
            This book does not exist.
        </s:elseif>
        <s:else>
        	<s:url action="Index" id="homepage"></s:url>
        	<s:a href="%{homepage}">[Return to Index]</s:a>
        	<s:url action="Profile" id="profile">
        		<s:param name="id" value="#parameters.id"></s:param>
        	</s:url>
        	<s:a href="%{profile}">[Return to Profile]</s:a>
            <div class="content">
                <s:property escape="false" value="content" />
            </div>
            <div class="navi">
                <s:url action="Reader" id="addbookmark">
                    <s:param name="id" value="#parameters.id"></s:param>
                    <s:param name="cp" value="1"></s:param>
                    <s:param name="wc" value="wordCount"></s:param>
                    <s:param name="action" value="'bookmark'"></s:param>
                </s:url>
                <s:a href="%{addbookmark}">[Add Book Mark]</s:a>
                <br />
                <s:if test="isFirstPage != true">
                    <s:url action="Reader" id="firstpageurl">
                        <s:param name="id" value="#parameters.id"></s:param>
                        <s:param name="cp" value="1"></s:param>
                        <s:param name="wc" value="wordCount"></s:param>
                    </s:url>
                    <s:a href="%{firstpageurl}">[First]</s:a>
                    <s:url action="Reader" id="prevpageurl">
                        <s:param name="id" value="#parameters.id"></s:param>
                        <s:param name="cp" value="curPage - 1"></s:param>
                        <s:param name="wc" value="wordCount"></s:param>
                    </s:url>
                    <s:a href="%{prevpageurl}">[Prev]</s:a>
                </s:if>
                <s:if test="isLastPage != true">
                    <s:url action="Reader" id="nextpageurl">
                        <s:param name="id" value="#parameters.id"></s:param>
                        <s:param name="cp" value="curPage + 1"></s:param>
                        <s:param name="wc" value="wordCount"></s:param>
                    </s:url>
                    <s:a href="%{nextpageurl}">[Next]</s:a>
                    <s:url action="Reader" id="lastpageurl">
                        <s:param name="id" value="#parameters.id"></s:param>
                        <s:param name="cp" value="pageCount"></s:param>
                        <s:param name="wc" value="wordCount"></s:param>
                    </s:url>
                    <s:a href="%{lastpageurl}">[Last]</s:a>
                </s:if>
            </div>
        </s:else>
    </body>
</html>
