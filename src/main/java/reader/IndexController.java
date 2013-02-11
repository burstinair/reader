/*
 * $Id: HelloWorld.template,v 1.2 2008-03-27 05:47:21 ub3rsold4t Exp $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package reader;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import model.Book;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import service.BookService;

/**
 * <code>Set welcome message.</code>
 */
public class IndexController extends ActionSupport {

    public HttpHeaders index() throws Exception {
        _books = BookService.getIndex();
        return new DefaultHttpHeaders(SUCCESS).disableCaching();
    }
    
    private ArrayList<Book> _books;
    public ArrayList<Book> getBooks()
    {
        return _books;
    }
}

