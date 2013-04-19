package burst.reader.web.action.model;

import burst.commons.model.PageModel;

public class PageModelImpl extends BaseModel implements PageModel {
	
	private Integer currentPage;
	private Integer pageSize;
	private Integer pageCount;

    private Integer defaultSize;

    private boolean showPageSizeInUrl;
	
	private int unboxedCurrentPage;
	private int unboxedPageSize;
	private int unboxedPageCount;

    public void setDefaultSize(Integer defaultSize) {
        this.defaultSize = defaultSize;
    }
    public boolean isShowPageSizeInUrl() {
        return showPageSizeInUrl;
    }
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
		if(currentPage == null) {
			this.unboxedCurrentPage = 0;
		} else {
			this.unboxedCurrentPage = currentPage;
		}
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		if(pageSize == null) {
			this.unboxedPageSize = 0;
		} else {
			this.unboxedPageSize = pageSize;
		}
        showPageSizeInUrl = pageSize != defaultSize;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
		if(pageCount == null) {
			this.unboxedPageCount = 0;
		} else {
			this.unboxedPageCount = pageCount;
		}
	}
	
	public int getUnboxedCurrentPage() {
		return unboxedCurrentPage;
	}
	public int getUnboxedPageSize() {
		return unboxedPageSize;
	}
	public int getUnboxedPageCount() {
		return unboxedPageCount;
	}
	
	public boolean isFirstPage() {
		return this.currentPage <= 1;
	}
	
	public boolean isLastPage() {
		return this.currentPage >= this.pageCount;
	}
}
