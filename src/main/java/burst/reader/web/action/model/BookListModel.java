package burst.reader.web.action.model;

import java.util.ArrayList;

import burst.reader.dto.BookDTO;

public class BookListModel extends PageModelImpl {

    private ArrayList<BookDTO> books;
    
    public void setBooks(ArrayList<BookDTO> books) {
		this.books = books;
	}
	public ArrayList<BookDTO> getBooks()
    {
        return books;
    }

}
