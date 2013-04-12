package burst.reader.service;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.transaction.annotation.Transactional;

import burst.reader.dto.BookMarkDTO;

public class BookMarkMonitorService implements Runnable, BeanFactoryAware {
	
	private ConcurrentLinkedQueue<BookMarkDTO> q;
	
	public void push(BookMarkDTO bookMark) {
		q.offer(bookMark);
	}
	
	public BookMarkMonitorService() {
		q = new ConcurrentLinkedQueue<BookMarkDTO>();
	}
	
	private BookMarkService bookMarkService;

	public void setBookMarkService(BookMarkService bookMarkService) {
		this.bookMarkService = bookMarkService;
	}

	public BookMarkService getBookMarkService() {
		return bookMarkService;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				continue;
			}
			BookMarkDTO bookMark = q.poll();
			if(bookMark == null) {
				continue;
			}
			try {
				bookMarkService.addBookMarkWithAuto(bookMark);
			}
			catch (SQLException ex) { }
		}
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		new Thread(this).run();
	}
}
