<#macro pager prefix model=model>

	<#if !model.lastPage>
	    <a href="${prefix}/${model.currentPage + 1}">下一页</a>
	</#if>
	<#if !model.firstPage>
	    <a href="${prefix}/${model.currentPage - 1}">上一页</a>
	</#if>
	[<a href="${prefix}/1">1</a>/${model.currentPage}/<a href="${prefix}/${model.pageCount}">${model.pageCount}</a>]

</#macro>

<#macro recent bookMark bookName>

	<#if bookMark??>
	    <li>
	        最近阅读：
	        <a href="/reader/${bookMark.bookId}/${bookMark.wordCount}/${bookMark.page}">
	            [${bookName} | 第 ${bookMark.page} 页 | 每页字数 ${bookMark.wordCount}]
	        </a>
	    </li>
	</#if>

</#macro>