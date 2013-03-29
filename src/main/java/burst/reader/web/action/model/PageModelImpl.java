package burst.reader.web.action.model;

import burst.web.model.PageModel;

public class PageModelImpl implements PageModel {
	
	private Integer currentPage;
	private Integer pageSize;
	private Integer pageCount;
	
	private int unboxedCurrentPage;
	private int unboxedPageSize;
	private int unboxedPageCount;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
		this.unboxedCurrentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		this.unboxedPageSize = pageSize;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
		this.unboxedPageCount = pageCount;
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
		return this.currentPage == 1;
	}
	
	public boolean isLastPage() {
		return this.currentPage == this.pageCount;
	}
	
}
