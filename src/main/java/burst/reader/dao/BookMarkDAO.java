/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import burst.reader.dto.BookMarkDTO;

/**
 *
 * @author Burst
 */
public class BookMarkDAO {
	
	private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ArrayList<BookMarkDTO> getBookMarks(int Id)
    {
        Session session = sessionFactory.openSession();
        ArrayList<BookMarkDTO> _bookmarks = new ArrayList<BookMarkDTO>();
        for (Object o : session.createQuery("from BookMarkDTO where book_id=" + Id + " order by is_auto_save, add_date desc").list()) {
            _bookmarks.add((BookMarkDTO)o);
        }
        session.close();
        return _bookmarks;
    }
	
	private void addSpecialBookMark(BookMarkDTO bookmark, String special)
	{
		bookmark.setId(0);
		bookmark.setIsAutoSave(special);
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            List list = session.createQuery(String.format("from BookMarkDTO where book_id=%d and is_auto_save='%s'", bookmark.getBookId(), special)).list();
            if (!list.isEmpty()) {
                bookmark.setId(((BookMarkDTO) list.get(0)).getId());
            }
            session.merge(bookmark);
            trans.commit();
        } catch (Exception ex) {
            try {
                trans.rollback();
            } catch (Exception ex2) {
            }
        } finally {
            try {
                session.flush();
                session.close();
            } catch (Exception ex) {
            }
        }
	}
    
    public synchronized void addAutoSaveBookMark(BookMarkDTO bookmark)
    {
		addSpecialBookMark(bookmark, "true");
    }

	public synchronized void addSingleBookMark(BookMarkDTO bookmark)
	{
		addSpecialBookMark(bookmark, "tsingle");
	}
    
    public synchronized void addBookMark(BookMarkDTO bookmark)
    {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            session.save(bookmark);
            trans.commit();
        } catch (Exception ex) {
            try {
                trans.rollback();
            } catch (Exception ex2) {
            }
        } finally {
            try {
                session.flush();
                session.close();
            } catch (Exception ex) {
            }
        }
    }
}
