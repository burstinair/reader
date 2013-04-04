/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ibatis.sqlmap.client.SqlMapClient;

import burst.reader.dto.BookMarkDTO;

/**
 *
 * @author Burst
 */
public class BookMarkService {
	
	private SqlMapClient sqlMapClient;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public List<BookMarkDTO> getBookMarks(int bookId) throws SQLException
    {
		return (List<BookMarkDTO>)sqlMapClient.queryForList("BookMarkDao.queryByBookId", bookId);
    }
	
	@Transactional
	public void addSpecialBookMark(BookMarkDTO bookmark, String special) throws SQLException
	{
		bookmark.setId(0);
		bookmark.setSpecial(special);

        Integer last_id = (Integer)sqlMapClient.queryForObject("BookMarkDao.loadIdByBookIdAndSpecial", bookmark);
        if (last_id != null) {
            bookmark.setId(last_id);
            sqlMapClient.update("BookMarkDao.update", bookmark);
        } else {
            sqlMapClient.insert("BookMarkDao.add", bookmark);
        }
	}
    
    public synchronized void addAutoSaveBookMark(BookMarkDTO bookmark) throws SQLException
    {
		addSpecialBookMark(bookmark, "auto");
    }

	public synchronized void addSingleBookMark(BookMarkDTO bookmark) throws SQLException
	{
		addSpecialBookMark(bookmark, "single");
	}
    
	@Transactional
    public synchronized void addBookMark(BookMarkDTO bookmark) throws SQLException
    {
        sqlMapClient.insert("BookMarkDao.add", bookmark);
    }

    public BookMarkDTO loadRecent() throws SQLException {
        return (BookMarkDTO)sqlMapClient.queryForObject("BookMarkDao.loadRecent");
    }

    public BookMarkDTO loadRecentByAuthor(String author) throws SQLException {
        return (BookMarkDTO)sqlMapClient.queryForObject("BookMarkDao.loadRecentByAuthor", author);
    }
}
