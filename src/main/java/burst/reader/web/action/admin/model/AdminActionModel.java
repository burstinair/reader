package burst.reader.web.action.admin.model;

import java.util.ArrayList;

import burst.reader.dto.BookDTO;
import burst.reader.web.action.model.PageModelImpl;

public class AdminActionModel extends PageModelImpl {

    private ArrayList<BookDTO> books;
    
    public void setBooks(ArrayList<BookDTO> books) {
		this.books = books;
	}
	public ArrayList<BookDTO> getBooks()
    {
        return books;
    }
}