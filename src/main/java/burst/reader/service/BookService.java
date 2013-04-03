/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ibatis.sqlmap.client.SqlMapClient;

import burst.commons.model.PageModel;
import burst.reader.BookException;
import burst.reader.dto.BookDTO;
import burst.reader.util.MaxCountLimitedMap;

/**
 *
 * @author Burst
 */
public class BookService {
	
	private SqlMapClient sqlMapClient;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

    @Transactional
    public List<BookDTO> getIndex(PageModel model) throws SQLException
    {
    	List<BookDTO> res = sqlMapClient.queryForList("BookDao.page", model);
    	int rowCount = (Integer)sqlMapClient.queryForObject("BookDao.count");
    	int pageSize = model.getPageSize();
        if(rowCount % pageSize != 0 || rowCount == 0)
        	model.setPageCount(rowCount / pageSize + 1);
        else
        	model.setPageCount(rowCount / pageSize);
        return res;
    }
    
    private MaxCountLimitedMap<Integer, BookDTO> _cache = new MaxCountLimitedMap<Integer, BookDTO>(10);
    
    public void update(int Id)
    {
        _cache.remove(Id);
    }
    
    @Transactional
    public void update(BookDTO book) throws BookException, SQLException
    {
    	sqlMapClient.update("BookDao.update", book);
        update(book.getId());
    }
    public void updateAll()
    {
        _cache.clear();
    }
    
    @Transactional
    public BookDTO getBookFromDb(int Id) throws BookException, SQLException
    {        
        BookDTO book = (BookDTO)sqlMapClient.queryForObject("BookDao.load", Id);
        if (book == null) {
            throw new BookException();
        }
        _cache.put(book.getId(), book);
        return book;
    }
    public BookDTO getBook(int Id) throws BookException, SQLException
    {
        if(_cache.containsKey(Id)) {
            return _cache.get(Id);
        }
        return getBookFromDb(Id);
    }
    public String getName(int Id) throws BookException, SQLException
    {
        return getBook(Id).getName();
    }
    public String getPagedContent(int Id, PageModel model) throws BookException, SQLException
    {
    	int cur_page = model.getCurrentPage();
    	int word_count = model.getPageSize();
        int start_pos = (cur_page - 1) * word_count, end_pos = start_pos + word_count;
        BookDTO book = getBook(Id);
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
    
    @Transactional
    public void addBook(BookDTO book) throws SQLException
    {
        sqlMapClient.insert("BookDao.add", book);
    }
    
    @Transactional
    public void deleteBook(int Id) throws SQLException
    {
        BookDTO book = new BookDTO();
        book.setId(Id);
        sqlMapClient.delete("BookDao.delete", book);
        sqlMapClient.delete("BookMarkDao.deleteByBookId", book);
        update(Id);
    }
}
