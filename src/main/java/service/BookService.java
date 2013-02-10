/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import model.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Burst
 */
public class BookService {
    
    private static ArrayList<Book> _index;
    public static ArrayList<Book> getIndex()
    {
        if(_index == null) {
            updateIndex();
        }
        return _index;
    }
    public static void updateIndex()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        _index = new ArrayList<Book>();
        for (Object o : session.createQuery("select id, name from Book").list()) {
            Book book = new Book();
            book.setId((Integer)((Object[])o)[0]);
            book.setName((String)((Object[])o)[1]);
            _index.add(book);
        }
        session.close();
    }
    
    private static ConcurrentHashMap<Integer, Book> _cache = new ConcurrentHashMap<Integer, Book>();
    
    public static void update(int Id)
    {
        _cache.remove(Id);
        updateIndex();
    }
    public static void update(Book book) throws BookException
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        session.save(book);
        trans.commit();
        session.close();
        update(book.getId());
        updateIndex();
    }
    public static void updateAll()
    {
        _cache.clear();
        updateIndex();
    }
    
    public static Book getBook(int Id) throws BookException
    {
        if(_cache.containsKey(Id)) {
            return _cache.get(Id);
        }
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = session.createQuery("from Book where id=" + Id).list();
        session.close();
        if (list.isEmpty()) {
            throw new BookException();
        }
        Book res = (Book)list.get(0);
        _cache.put(res.getId(), res);
        return res;
    }
    public static String getName(int Id) throws BookException
    {
        return getBook(Id).getName();
    }
    public static String getContent(int Id, int page, int word_count) throws BookException
    {
        int start_pos = (page - 1) * word_count, end_pos = start_pos + word_count;
        String content = getBook(Id).getContent();
        if (content.length() < start_pos) {
            return "";
        }
        if (content.length() < end_pos) {
            end_pos = content.length();
        }
        return content.substring(start_pos, end_pos);
    }
    public static int getPageCount(int Id, int word_count) throws BookException
    {
        int total = getBook(Id).getContent().length();
        if(total % word_count == 0) {
            return total / word_count;
        }
        else {
            return total / word_count + 1;
        }
    }
    
    public static void addBook(Book book)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        session.save(book);
        trans.commit();
        session.flush();
        session.close();
        updateIndex();
    }
    
    public static void deleteBook(int Id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        Book book = new Book();
        book.setId(Id);
        session.delete(book);
        Query q = session.createQuery("delete BookMark where book_id = ?");
        q.setInteger(0, Id);
        q.executeUpdate();
        trans.commit();
        session.flush();
        session.close();
        update(Id);
        updateIndex();
    }
}
