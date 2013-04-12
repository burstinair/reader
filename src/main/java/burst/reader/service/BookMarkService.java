/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.service;

import java.sql.SQLException;
import java.util.List;

import burst.reader.dto.BookMarkDTO;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Burst
 */
public class BookMarkService {

	public static final String NORMAL = "znormal";
	public static final String SINGLE = "single";
	public static final String AUTO = "auto";
	
	private SqlMapClient sqlMapClient;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public List<BookMarkDTO> loadBookMarks(int bookId) throws SQLException
    {
		return (List<BookMarkDTO>)sqlMapClient.queryForList("BookMarkDao.queryByBookId", bookId);
    }
	
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
    
    public void addAutoSaveBookMark(BookMarkDTO bookmark) throws SQLException
    {
		addSpecialBookMark(bookmark, AUTO);
    }

	public void addSingleBookMark(BookMarkDTO bookmark) throws SQLException
	{
		addSpecialBookMark(bookmark, SINGLE);
	}
    
    public void addBookMark(BookMarkDTO bookmark) throws SQLException
    {
        sqlMapClient.insert("BookMarkDao.add", bookmark);
    }

	public void addBookMarkWithAuto(BookMarkDTO bookMark) throws SQLException {
		if(BookMarkService.SINGLE.equals(bookMark.getSpecial())) {
			addSingleBookMark(bookMark);
		} else if(BookMarkService.NORMAL.equals(bookMark.getSpecial())) {
			addBookMark(bookMark);
		}
		addAutoSaveBookMark(bookMark);
	}

    public BookMarkDTO loadRecent() throws SQLException {
        return (BookMarkDTO)sqlMapClient.queryForObject("BookMarkDao.loadRecent");
    }

    public BookMarkDTO loadRecentByAuthor(String author) throws SQLException {
        return (BookMarkDTO)sqlMapClient.queryForObject("BookMarkDao.loadRecentByAuthor", author);
    }
}
