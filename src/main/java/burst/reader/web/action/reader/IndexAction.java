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
import burst.reader.web.action.BaseAction;
import burst.reader.web.action.reader.model.IndexActionModel;

import com.opensymphony.xwork2.ModelDriven;

public class IndexAction extends BaseAction implements ModelDriven<IndexActionModel> {
	
	private static final long serialVersionUID = -7060433070277387333L;

	public String execute() throws Exception {

        BookMarkDTO recent = bookMarkService.loadRecent();

        if(recent != null) {
            model.setRecentBookMark(recent);
            model.setRecentBookName(bookService.getName(recent.getBookId()));
        }

        if("recent".equals(model.getAction())) {
            return "recent";
        }

		if(model.getCurrentPage() == null) {
			model.setCurrentPage(1);
		}

        if(model.getAuthor() != null && !"".equals(model.getAuthor())) {
            model.setBooks(bookService.getVisibleByAuthorIndex(model, model.getAuthor()));
        } else {
            model.setBooks(bookService.getVisibleIndex(model));
        }

        return SUCCESS;
    }

    private IndexActionModel model;

	@Override
	public IndexActionModel getModel() {
		return model;
	}

	public void setModel(IndexActionModel model) {
		this.model = model;
	}
}

