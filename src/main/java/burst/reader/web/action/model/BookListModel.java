package burst.reader.web.action.model;

import java.util.List;

import burst.reader.dto.BookDTO;

public class BookListModel extends PageModelImpl {

    private List<BookDTO> books;
    
    public void setBooks(List<BookDTO> books) {
		this.books = books;
	}
	public List<BookDTO> getBooks()
    {
        return books;
    }

}
