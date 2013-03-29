<#if !lastPage>
    [<a href="${pagerPrefix}/${currentPage + 1}">下一页</a>]
</#if>
<#if !firstPage>
    [<a href="${pagerPrefix}/${currentPage - 1}">上一页</a>]
</#if>
[<a href="${pagerPrefix}/1">1</a>/${currentPage}/<a href="${pagerPrefix}/${pageCount}">${pageCount}</a>]