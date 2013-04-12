/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.service;

import java.sql.SQLException;
import java.util.List;

import burst.commons.model.PageModel;
import burst.reader.BookException;
import burst.reader.dto.BookDTO;
import burst.reader.util.MaxCountLimitedMap;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Burst
 */
public class BookService {
	
	private SqlMapClient sqlMapClient;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

    private void buildPageModel(PageModel model, Integer rowCount) {
        int pageSize = model.getPageSize();
        if(rowCount % pageSize != 0 || rowCount == 0)
            model.setPageCount(rowCount / pageSize + 1);
        else
            model.setPageCount(rowCount / pageSize);
    }

    public List<BookDTO> loadIndex(PageModel model) throws SQLException
    {
    	List<BookDTO> res = sqlMapClient.queryForList("BookDao.page", model);
    	buildPageModel(model, (Integer)sqlMapClient.queryForObject("BookDao.count"));
        return res;
    }

    public List<BookDTO> loadVisibleIndex(PageModel model) throws SQLException
    {
        List<BookDTO> res = sqlMapClient.queryForList("BookDao.pageVisible", model);
        buildPageModel(model, (Integer)sqlMapClient.queryForObject("BookDao.countVisible"));
        return res;
    }

    public List<BookDTO> loadVisibleByAuthorIndex(PageModel model, String author) throws SQLException
    {
        List<BookDTO> res = sqlMapClient.queryForList("BookDao.pageVisibleByAuthor", model);
        buildPageModel(model, (Integer)sqlMapClient.queryForObject("BookDao.countVisibleByAuthor", author));
        return res;
    }

    private MaxCountLimitedMap<Integer, BookDTO> _cache = new MaxCountLimitedMap<Integer, BookDTO>(10);
        
    public void update(BookDTO book) throws BookException, SQLException
    {
    	sqlMapClient.update("BookDao.update", book);
        _cache.remove(book.getId());
    }
    
    public BookDTO loadBookFromDb(int Id) throws BookException, SQLException
    {
        BookDTO book = (BookDTO)sqlMapClient.queryForObject("BookDao.load", Id);
        if (book == null) {
            throw new BookException();
        }
        _cache.put(book.getId(), book);
        return book;
    }
    public BookDTO loadBook(int Id) throws BookException, SQLException
    {
        if(_cache.containsKey(Id)) {
            return _cache.get(Id);
        }
        return loadBookFromDb(Id);
    }
    public String loadName(int Id) throws BookException, SQLException
    {
        return loadBook(Id).getName();
    }
    public String loadPagedContent(int Id, PageModel model) throws BookException, SQLException
    {
    	int cur_page = model.getCurrentPage();
    	int word_count = model.getPageSize();
        int start_pos = (cur_page - 1) * word_count, end_pos = start_pos + word_count;
        BookDTO book = loadBook(Id);
        int total = book.getContent().length();
        if(total % word_count == 0) {
            model.setPageCount(total / word_count);
        }
        else {
            model.setPageCount(total / word_count + 1);
        }
        String content = book.getContent();
        if (content.length() < start_pos) {
            return "";
        }
        if (content.length() < end_pos) {
            end_pos = content.length();
        }
        return content.substring(start_pos, end_pos);
    }
    
    public void addBook(BookDTO book) throws SQLException
    {
        sqlMapClient.insert("BookDao.add", book);
        book.setId((Integer)sqlMapClient.queryForObject("BookDao.lastInsertId"));
    }
    
    public void deleteBook(int Id) throws SQLException
    {
        BookDTO book = new BookDTO();
        book.setId(Id);
        sqlMapClient.delete("BookDao.delete", book);
        sqlMapClient.delete("BookMarkDao.deleteByBookId", book);
        _cache.remove(Id);
    }

    public void updateVisible(Integer bookId, String b) throws SQLException {
        BookDTO book = new BookDTO();
        book.setId(bookId);
        book.setVisible(b);
        sqlMapClient.update("BookDao.setVisible", book);
    }
}
