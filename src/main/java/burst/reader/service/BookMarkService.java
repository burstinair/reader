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

	public List<BookMarkDTO> getBookMarks(int Id) throws SQLException
    {
		return (List<BookMarkDTO>)sqlMapClient.queryForList("BookMarkDao.queryByBookId", Id);
    }
	
	@Transactional
	public void addSpecialBookMark(BookMarkDTO bookmark, String special) throws SQLException
	{
		bookmark.setId(0);
		bookmark.setSpecial(special);
		
        BookMarkDTO last_bookmark = (BookMarkDTO)sqlMapClient.queryForObject("BookMarkDao.loadByBookIdAndSpecial");
        if (last_bookmark != null) {
            bookmark.setId(last_bookmark.getId());
        }
        sqlMapClient.update("BookMarkDao.update", bookmark);
	}
    
    public synchronized void addAutoSaveBookMark(BookMarkDTO bookmark) throws SQLException
    {
		addSpecialBookMark(bookmark, "true");
    }

	public synchronized void addSingleBookMark(BookMarkDTO bookmark) throws SQLException
	{
		addSpecialBookMark(bookmark, "tsingle");
	}
    
	@Transactional
    public synchronized void addBookMark(BookMarkDTO bookmark) throws SQLException
    {
        sqlMapClient.insert("BookMarkDao.add", bookmark);
    }
}
