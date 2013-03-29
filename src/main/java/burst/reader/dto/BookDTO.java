/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Burst
 */
@Entity
@Table(name = "book")
public class BookDTO implements Serializable {
    
	private static final long serialVersionUID = 7760145660891108552L;
	
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
    
    @Column(name = "name")
    private String name;
    public String getName()
    {
        return name;
    }
    public void setName(String value)
    {
        name = value;
    }
    
    @Column(name = "content")
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
