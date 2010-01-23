// Copyright 2010 Thiago H. de Paula Figueiredo
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package br.com.machina.verbum.pages.blog;

import java.util.List;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Mixin;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import br.com.machina.verbum.dao.BlogDAO;
import br.com.machina.verbum.entities.Blog;
import br.com.machina.verbum.entities.User;
import br.com.machina.verbum.mixins.RedirectToLoginIfUnlogged;

/**
 * Page that lists blogs.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class ListBlog {
	
	@Mixin
	@SuppressWarnings("unused")
	private RedirectToLoginIfUnlogged redirectToLoginIfUnlogged;
	
	@SessionState(create = false)
	private User user;

	@Persist(PersistenceConstants.FLASH)
	private String message;
	
	@Inject
	private BlogDAO blogDAO;
	
	@Property
	@SuppressWarnings("unused")
	private Blog blog;
	
	/**
	 * Returns all the blogs owned by the logged user.
	 * 
	 * @return a {@link List} of {@link Blog}s.
	 */
	public List<Blog> getBlogs() {
		return blogDAO.findByOwner(user);
	}

	/**
	 * Returns the value of the <code>message</code> property.
	 * 
	 * @return a {@link String}.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Changes the value of the <code>message</code> property.
	 * 
	 * @param message a {@link String}.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
