/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.dto;

import java.io.Serializable;

/**
 *
 * @author Burst
 */
public class BookDTO implements Serializable {
    
	private static final long serialVersionUID = 7760145660891108552L;
	
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
    
    private Boolean visible;
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public Boolean getVisible() {
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
