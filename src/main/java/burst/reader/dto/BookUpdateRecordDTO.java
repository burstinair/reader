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
public class BookUpdateRecordDTO implements Serializable {
    
	private static final long serialVersionUID = -5663823481587712487L;
	
    private Integer id;
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer value)
    {
        id = value;
    }
    
    private Integer bookId;
    public Integer getBookId()
    {
        return bookId;
    }
    public void setBookId(Integer value)
    {
        bookId = value;
    }

    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
    }

    private Integer currentSize;

    private Date addDate;
    public Date getAddDate()
    {
        return addDate;
    }
    public void setAddDate(Date value)
    {
        addDate = value;
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
