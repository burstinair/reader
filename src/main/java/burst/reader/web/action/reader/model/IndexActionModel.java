package burst.reader.web.action.reader.model;

import java.util.ArrayList;

import burst.reader.dto.BookDTO;
import burst.reader.web.action.model.PageModelImpl;

public class IndexActionModel extends PageModelImpl {

    private ArrayList<BookDTO> books;
    
    public void setBooks(ArrayList<BookDTO> books) {
		this.books = books;
	}
	public ArrayList<BookDTO> getBooks()
    {
        return books;
    }
}