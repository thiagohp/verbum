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

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Mixin;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import br.com.machina.verbum.dao.BlogDAO;
import br.com.machina.verbum.entities.Blog;
import br.com.machina.verbum.entities.User;
import br.com.machina.verbum.mixins.RedirectToLoginIfUnlogged;
import br.com.machina.verbum.pages.Initial;

/**
 * Class that edits or creates blogs.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class EditBlog {

	@Mixin
	@SuppressWarnings("unused")
	private RedirectToLoginIfUnlogged redirectToLoginIfUnlogged;

	@Property
	private Blog blog;

	@SessionState(create = false)
	private User user;

	@Inject
	private BlogDAO blogDAO;

	@Inject
	private Messages messages;

	@InjectComponent
	private Form form;

	@InjectPage
	private ListBlog listBlog;

	/**
	 * Activates the page. Redirects to the initial page if the edited blog is owned by another
	 * user.
	 */
	Object onActivate(EventContext context) {

		if (context.getCount() > 0) {

			String stringId = context.get(String.class, 0);
			blog = blogDAO.findByStringId(stringId);

			if (blog != null && blog.getOwner().equals(user) == false) {
				return Initial.class;
			}

		}

		return null;

	}

	/**
	 * Saves or updates the blog.
	 */
	@OnEvent(EventConstants.SUCCESS)
	Object saveOrUpdate() {

		blogDAO.saveOrUpdate(blog);
		listBlog.setMessage(messages.get("message.success.saveorupdate.blog"));

		return listBlog;

	}

	/**
	 * Validates the stringId property.
	 */
	@OnEvent(EventConstants.VALIDATE_FORM)
	void validate() {

		String stringId = blog.getStringId();

		if (stringId != null) {

			Blog existing = blogDAO.findByStringId(stringId);

			if (existing != null && existing.getId().equals(blog.getId()) == false) {

				final String message = messages.format("message.error.duplicate.stringid", stringId);
				form.recordError(message);

			}

		}

	}

	/**
	 * Instantiates the blog if needed.
	 */
	@OnEvent(EventConstants.PREPARE)
	void prepare() {

		if (blog == null) {
			blog = new Blog();
			blog.setOwner(user);
		}

	}
	
	/**
	 * Returns the activation context value for this page: the blog's stringId.
	 */
	Object onPassivate() {
		return blog.getStringId();
	}

}
