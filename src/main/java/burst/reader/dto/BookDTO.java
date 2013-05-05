/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Burst
 */
public class BookDTO implements Serializable {
    
	private static final long serialVersionUID = 7760145660891108552L;
	public static final String VISIBLE = "visible";
    public static final String UNVISIBLE = "unvisible";

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String author;

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    private Date addDate;
    
    private String visible;
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getVisible() {
		return visible;
	}
    
    private String content;
    public String getContent()
    {
        return content;
    }
    public void setContent(String value)
    {
        content = value;
    }
}
