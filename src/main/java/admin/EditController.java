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

import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.rest.DefaultHttpHeaders;
import service.BookException;
import service.BookService;
import service.Util;


/**
 *
 * @author Burst
 */
public class EditController extends ActionSupport {

    public HttpHeaders index() throws Exception {
        id = 0;
        return new DefaultHttpHeaders(SUCCESS).disableCaching();
    }

    public String show() {
        try {
            book = BookService.getBook(id);
        } catch (Exception ex) {
            book = null;
            id = 0;
        }
        return SUCCESS;
    }

    public String update() throws BookException
    {
        boolean isAdd = id == null || id == 0;
        book = null;
        boolean isSubmit = true;
        try {
            book = new Book();
            book.setId(id);
            book.setName(name);
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

    private Integer id;
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
    }

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
