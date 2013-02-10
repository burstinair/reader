<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:property value="bookName" default="���鲻���ڡ�" /></title>
    </head>
    <body>
        <s:if test="#action == null">
            ���鲻���ڡ�
        </s:if>
        <s:elseif test="notExist">
            ���鲻���ڡ�
        </s:elseif>
        <s:else>
        	<s:url action="Index" id="homepage"></s:url>
        	<s:a href="%{homepage}">[������ҳ]</s:a>
            <s:url action="Reader" id="fromstart">
                <s:param name="id" value="#parameters.id"></s:param>
            </s:url>
            <ul>
                <li><s:a href="%{fromstart}">[�ӿ�ʼ�Ķ�]</s:a></li>
                <s:iterator value="bookMarks" id="mark">
                    <s:url action="Reader" id="markurl">
                        <s:param name="id" value="#mark.bookId"></s:param>
                        <s:param name="cp" value="#mark.page"></s:param>
                        <s:param name="wc" value="#mark.wordCount"></s:param>
                    </s:url>
                    <li><s:a href="%{markurl}">[�� <s:property value="#mark.page" /> ҳ | ÿҳ���� <s:property value="#mark.wordCount" />]</s:a></li>
                </s:iterator>
            </ul>
        </s:else>
    </body>
</html>
