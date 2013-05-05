<head>
    <title>批量添加</title>
</head>
<body>
    <#include "addEditNavi.ftl">
    <div>
        <#if success??>
            <#if success>
                添加成功。
            <#else>
                添加失败。
            </#if>
        </#if>
    </div>
    <div>
        上传的文件需为文件名编码为GBK的zip格式，其中包含若干文件夹，文件夹名为作者名，每个文件夹中有若干个文件，每个文件为一本书，文件名为书名，txt文件会以去除后缀的文件名为书名。
    </div>
    <@s.form method="post" action="/admin/addMultSubmit" enctype="multipart/form-data">
        <@s.textfield label="版本" name="version" value="%{version}" />
        <@s.file name="upload" label="选择文件(*.zip)"></@s.file>
        <@s.submit label="提交" />
    </@s.form>
</body>