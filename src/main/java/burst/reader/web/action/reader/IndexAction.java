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

package burst.reader.web.action.reader;

import burst.reader.dto.BookMarkDTO;
import burst.reader.dto.BookDTO;
import burst.reader.web.action.BaseAction;
import burst.reader.web.action.reader.model.IndexActionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

public class IndexAction extends BaseAction implements ModelDriven<IndexActionModel> {
	
	private static final long serialVersionUID = -7060433070277387333L;

    public String recent() throws Exception {
        indexActionModel.setRecentBookMark(bookMarkService.loadRecent());
        return SUCCESS;
    }

	public String execute() throws Exception {

        BookMarkDTO recent = bookMarkService.loadRecent();

        if(recent != null) {
            indexActionModel.setRecentBookMark(recent);
            indexActionModel.setRecentBookName(bookService.loadName(recent.getBookId()));
        }

		if(indexActionModel.getCurrentPage() == null) {
            indexActionModel.setCurrentPage(1);
		}

        List<BookDTO> books = null;

        books = bookService.loadVisibleIndex(indexActionModel);

        Map<String, List<BookDTO>> res_books = new HashMap<String, List<BookDTO>>();
        for(BookDTO book : books) {
            if(!res_books.containsKey(book.getAuthor())) {
                res_books.put(book.getAuthor(), new ArrayList<BookDTO>());
            }
            res_books.get(book.getAuthor()).add(book);
        }
        indexActionModel.setBooks(res_books);

        return SUCCESS;
    }

    private IndexActionModel indexActionModel;

	@Override
	public IndexActionModel getModel() {
		return indexActionModel;
	}

	public void setIndexActionModel(IndexActionModel indexActionModel) {
		this.indexActionModel = indexActionModel;
	}
}

