/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import burst.reader.BookException;
import burst.reader.dto.BookDTO;
import burst.web.model.PageModel;

/**
 *
 * @author Burst
 */
public class BookDAO {
	
	public SessionFactory getSessionFactory() {
		return SessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		SessionFactory = sessionFactory;
	}

	private SessionFactory SessionFactory;
    
    public ArrayList<BookDTO> getIndex(PageModel model)
    {
        Session session = SessionFactory.openSession();
        ArrayList<BookDTO> index = new ArrayList<BookDTO>();
        Query q = session.createQuery("select id, name from BookDTO order by id desc");
        q.setFirstResult((model.getCurrentPage() - 1) * model.getPageSize());
        q.setMaxResults(model.getPageSize());
        for (Object o : q.list()) {
            BookDTO book = new BookDTO();
            book.setId((Integer)((Object[])o)[0]);
            book.setName((String)((Object[])o)[1]);
            index.add(book);
        }
        Criteria crit = session.createCriteria(BookDTO.class);
        crit.setProjection(Projections.rowCount());
        int bookCount = (int)(long)(Long)crit.uniqueResult();
        session.close();
        if(bookCount % model.getUnboxedPageSize() != 0 || bookCount == 0)
        	model.setPageCount(bookCount / model.getUnboxedPageSize() + 1);
        else
        	model.setPageCount(bookCount / model.getUnboxedPageSize());
        return index;
    }
    
    private ConcurrentHashMap<Integer, BookDTO> _cache = new ConcurrentHashMap<Integer, BookDTO>();
    
    public void update(int Id)
    {
        _cache.remove(Id);
    }
    public void update(BookDTO book) throws BookException
    {
        Session session = SessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        session.merge(book);
        trans.commit();
        session.close();
        update(book.getId());
    }
    public void updateAll()
    {
        _cache.clear();
    }
    
    public BookDTO getBook(int Id) throws BookException
    {
        if(_cache.containsKey(Id)) {
            return _cache.get(Id);
        }
        
        Session session = SessionFactory.openSession();
        List<BookDTO> list = session.createQuery("from BookDTO where id=" + Id).list();
        session.close();
        if (list.isEmpty()) {
            throw new BookException();
        }
        BookDTO res = list.get(0);
        _cache.put(res.getId(), res);
        return res;
    }
    public String getName(int Id) throws BookException
    {
        return getBook(Id).getName();
    }
    public String getPagedContent(int Id, PageModel model) throws BookException
    {
    	int cur_page = model.getCurrentPage();
    	int word_count = model.getPageSize();
        int start_pos = (cur_page - 1) * word_count, end_pos = start_pos + word_count;
        String content = getBook(Id).getContent();
        if (content.length() < start_pos) {
            return "";
        }
        if (content.length() < end_pos) {
            end_pos = content.length();
        }
        int total = getBook(Id).getContent().length();
        if(total % word_count == 0) {
            model.setPageCount(total / word_count);
        }
        else {
            model.setPageCount(total / word_count + 1);
        }
        return content.substring(start_pos, end_pos);
    }
    
    public void addBook(BookDTO book)
    {
        Session session = SessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        session.save(book);
        trans.commit();
        session.flush();
        session.close();
    }
    
    public void deleteBook(int Id)
    {
        Session session = SessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        BookDTO book = new BookDTO();
        book.setId(Id);
        session.delete(book);
        Query q = session.createQuery("delete BookMarkDTO where book_id = ?");
        q.setInteger(0, Id);
        q.executeUpdate();
        trans.commit();
        session.flush();
        session.close();
        update(Id);
    }
}
