/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import burst.commons.model.PageModel;
import burst.reader.BookException;
import burst.reader.dto.BookDTO;
import burst.reader.dto.BookUpdateRecordDTO;
import burst.web.util.MaxCountLimitedMap;

import burst.web.model.RemoteModel;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Burst
 */
public class BookService {

    public static final int MAX_BOOK_COUNT_IN_CACHE = 10;
    public static final String SPECIAL_INIT = "init";
    public static final String SPECIAL_DELETE = "delete";
    public static final String SPECIAL_UPDATE = "update";
    public static final String VERSION_INIT = "version_init";
    public static final String VERSION_DELETE = "version_delete";
    private static final Integer CONTENT_SIZE_NOCHANGE = -1;

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

    private MaxCountLimitedMap<Integer, BookDTO> _cache = new MaxCountLimitedMap<Integer, BookDTO>(MAX_BOOK_COUNT_IN_CACHE);

    public void addBookUpdateRecord(Integer bookId, Integer currentSize, String version, RemoteModel remoteModel, String special) throws SQLException {
        BookUpdateRecordDTO record = new BookUpdateRecordDTO();
        record.setAddDate(new Date());
        record.setBookId(bookId);
        record.setSpecial(special);
        record.setIp(remoteModel.getRemoteAddr());
        record.setUserAgent(remoteModel.getUserAgent());
        BookUpdateRecordDTO last = null;
        if(version == null || currentSize == CONTENT_SIZE_NOCHANGE) {
            last = loadLastUpdateRecord(bookId);
        }
        if(version == null) {
            if(last == null) {
                record.setVersion(VERSION_INIT);
            } else {
                record.setVersion(last.getVersion());
            }
        } else {
            record.setVersion(version);
        }
        if(currentSize == CONTENT_SIZE_NOCHANGE) {
            if(last == null) {
                record.setCurrentSize(0);
            } else {
                record.setCurrentSize(last.getCurrentSize());
            }
        } else {
            record.setCurrentSize(currentSize);
        }
        sqlMapClient.insert("BookUpdateRecordDao.add", record);
    }

    public void update(BookDTO book) throws BookException, SQLException
    {
        sqlMapClient.update("BookDao.update", book);
        _cache.remove(book.getId());
    }

    public void updateWithoutContent(BookDTO book) throws BookException, SQLException
    {
        sqlMapClient.update("BookDao.updateWithoutContent", book);
        _cache.remove(book.getId());
    }

    public void updateAndAddRecord(BookDTO book, RemoteModel remoteModel, String version) throws BookException, SQLException {
        addBookUpdateRecord(book.getId(), book.getContent().length(), version, remoteModel, SPECIAL_UPDATE);
        update(book);
    }

    public void updateWithoutContentAndAddRecord(BookDTO book, RemoteModel remoteModel, String version) throws BookException, SQLException {
        addBookUpdateRecord(book.getId(), CONTENT_SIZE_NOCHANGE, version, remoteModel, SPECIAL_UPDATE);
        updateWithoutContent(book);
    }

    public List<BookUpdateRecordDTO> loadHistory(int Id) throws SQLException {
        return sqlMapClient.queryForList("BookUpdateRecordDao.queryByBookId", Id);
    }

    public BookUpdateRecordDTO loadLastUpdateRecord(int Id) throws SQLException {
        return (BookUpdateRecordDTO)sqlMapClient.queryForObject("BookUpdateRecordDao.queryLastByBookId", Id);
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
        book.setId((Integer) sqlMapClient.queryForObject("BookDao.lastInsertId"));
    }

    public void addBookAndAddRecord(BookDTO book, RemoteModel remoteModel, String version) throws SQLException {
        addBookUpdateRecord(book.getId(), book.getContent().length(), version, remoteModel, SPECIAL_INIT);
        addBook(book);
    }
    
    public void deleteBook(int Id) throws SQLException
    {
        sqlMapClient.delete("BookDao.delete", Id);
        sqlMapClient.delete("BookMarkDao.deleteByBookId", Id);
        _cache.remove(Id);
    }

    public void deleteBookAndAddRecord(int Id, RemoteModel remoteModel, String version) throws SQLException {
        addBookUpdateRecord(Id, 0, version, remoteModel, SPECIAL_DELETE);
        deleteBook(Id);
    }

    public void updateVisible(Integer bookId, String b) throws SQLException {
        BookDTO book = new BookDTO();
        book.setId(bookId);
        book.setVisible(b);
        sqlMapClient.update("BookDao.setVisible", book);
    }
}
