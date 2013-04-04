<#if recentBookMark??>
    <li>
        最近阅读：
        <a href="/reader/${recentBookMark.bookId}/${recentBookMark.wordCount}/${recentBookMark.page}">
            [${recentBookName} | 第 ${recentBookMark.page} 页 | 每页字数 ${recentBookMark.wordCount}]
        </a>
    </li>
</#if>