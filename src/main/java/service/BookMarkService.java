/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import model.BookMark;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Burst
 */
public class BookMarkService {
    
    public static ArrayList<BookMark> getBookMarks(int Id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<BookMark> _bookmarks = new ArrayList<BookMark>();
        for (Object o : session.createQuery("from BookMark where book_id=" + Id + " order by is_auto_save, add_date desc").list()) {
            _bookmarks.add((BookMark)o);
        }
        session.close();
        return _bookmarks;
    }
    
    public static void addAutoSaveBookMark(BookMark bookmark)
    {
        Session session = null;
        Transaction trans = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            List list = session.createQuery("from BookMark where book_id=" + bookmark.getBookId() + " and is_auto_save='true'").list();
            if (!list.isEmpty()) {
                bookmark.setId(((BookMark) list.get(0)).getId());
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
    
    public static void addBookMark(BookMark bookmark)
    {
        Session session = null;
        Transaction trans = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
