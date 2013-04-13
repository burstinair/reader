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
public class BookMarkDTO implements Serializable {
    
	private static final long serialVersionUID = -5663823481587712387L;
	
    private Integer id;
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer value)
    {
        id = value;
    }
    
    private Integer book_id;
    public Integer getBookId()
    {
        return book_id;
    }
    public void setBookId(Integer value)
    {
        book_id = value;
    }
    
    private Integer page;
    public Integer getPage()
    {
        return page;
    }
    public void setPage(Integer value)
    {
        page = value;
    }
    
    private Integer word_count;
    public Integer getWordCount()
    {
        return word_count;
    }
    public void setWordCount(Integer value)
    {
        word_count = value;
    }
    
    private Date add_date;
    public Date getAddDate()
    {
        return add_date;
    }
    public void setAddDate(Date value)
    {
        add_date = value;
    }
    
    private String special;
    public String getSpecial()
    {
        return this.special;
    }
    public void setSpecial(String special)
    {
    	this.special = special;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private String ip;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    private String userAgent;
}
