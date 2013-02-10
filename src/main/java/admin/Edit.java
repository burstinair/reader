/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import model.Book;
import java.io.File;
import java.io.FileInputStream;
import java.nio.CharBuffer;
import service.BookService;
import service.Util;

/**
 *
 * @author Burst
 */
public class Edit extends ActionSupport {
    
    @Override
    public String execute() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> params = context.getParameters();
        
        int id = 0;
        boolean isAdd = false;
        try {
            id = Integer.parseInt(((String[])params.get("id"))[0]);
        } catch (Exception ex) {
            isAdd = true;
        }
        book = null;
        boolean isSubmit = true;
        try {
            book = new Book();
            book.setId(id);
            book.setName(((String[]) params.get("name"))[0]);
            book.setContent(Util.readAllText(upload, Charset.forName("GBK")));
        } catch (Exception ex) {
            isSubmit = false;
        }
        if (isSubmit) {
            if (isAdd) {
                BookService.addBook(book);
            } else {
                BookService.update(book);
            }
        } else {
            if (!isAdd) {
                book = BookService.getBook(id);
            }
        }

        return SUCCESS;
    }

    /*private Integer id;
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer value)
    {
        id = value;
    }

    private String name;
    public String getName()
    {
        return name;
    }
    public void setName(String value)
    {
        name = value;
    }*/

    private File upload;
    public File getUpload()
    {
        return upload;
    }
    public void setUpload(File value)
    {
        upload = value;
    }

    private String uploadFileName;
    public String getUploadFileName()
    {
        return uploadFileName;
    }
    public void setUploadFileName(String value)
    {
        uploadFileName = value;
    }

    private String uploadContentType;
    public String getUploadContentType()
    {
        return uploadContentType;
    }
    public void setUploadContentType(String value)
    {
        uploadContentType = value;
    }

    private Book book;
    public Book getBook()
    {
        return book;
    }
}
