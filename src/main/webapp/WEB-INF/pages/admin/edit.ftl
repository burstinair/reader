<#assign s=JspTaglibs["/WEB-INF/tld/struts-tags.tld"]>
<head>
    <title>${("编辑" + book.name)!"添加新书"}</title>
</head>
<body>
    <#include "addEditNavi.ftl">
    <div>
        <#if add??>
            <#if add>
                添加
            <#else>
                更新
            </#if>
        </#if>
        <#if success??>
            <#if success>
                成功。
            <#else>
                失败。
            </#if>
        </#if>
    </div>
    <@s.form method="post" action="/admin/editSubmit" enctype="multipart/form-data">
        <@s.hidden name="id" value="%{book.id}" />
        <@s.textfield label="书名" name="name" value="%{book.name}" />
        <@s.textfield label="作者" name="author" value="%{book.author}" />
        <@s.textfield label="版本" name="version" value="%{version}" />
        <@s.file name="upload" label="选择文件(*.txt)，留空则不更新内容。"></@s.file>
        <@s.submit label="提交" />
    </@s.form>
</body>