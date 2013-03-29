/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Burst
 */
@Entity
@Table(name = "book_mark")
public class BookMarkDTO implements Serializable {
    
	private static final long serialVersionUID = -5663823481587712387L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer value)
    {
        id = value;
    }
    
    @Column(name = "book_id")
    private Integer book_id;
    public Integer getBookId()
    {
        return book_id;
    }
    public void setBookId(Integer value)
    {
        book_id = value;
    }
    
    @Column(name = "page")
    private Integer page;
    public Integer getPage()
    {
        return page;
    }
    public void setPage(Integer value)
    {
        page = value;
    }
    
    @Column(name = "word_count")
    private Integer word_count;
    public Integer getWordCount()
    {
        return word_count;
    }
    public void setWordCount(Integer value)
    {
        word_count = value;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_date")
    private Date add_date;
    public Date getAddDate()
    {
        return add_date;
    }
    public void setAddDate(Date value)
    {
        add_date = value;
    }
    
    @Column(name = "is_auto_save")
    private String is_auto_save;
    public String getIsAutoSave()
    {
        return is_auto_save;
    }
    public void setIsAutoSave(String value)
    {
        is_auto_save = value;
    }
}
