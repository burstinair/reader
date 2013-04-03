package burst.commons.model;

public interface PageModel {

	Integer getCurrentPage();
	Integer getPageSize();
	Integer getPageCount();
	
	void setCurrentPage(Integer currentPage);
	void setPageSize(Integer pageSize);
	void setPageCount(Integer pageCount);
}
