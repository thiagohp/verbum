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

package br.com.machina.verbum.pages.post;

import java.util.Date;

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

import br.com.machina.verbum.dao.PostDAO;
import br.com.machina.verbum.entities.Post;
import br.com.machina.verbum.entities.User;
import br.com.machina.verbum.mixins.RedirectToLoginIfUnlogged;
import br.com.machina.verbum.pages.Initial;

/**
 * Class that edits or creates posts.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class EditPost {

	@Mixin
	@SuppressWarnings("unused")
	private RedirectToLoginIfUnlogged redirectToLoginIfUnlogged;

	@Property
	private Post post;

	@SessionState(create = false)
	private User user;

	@Inject
	private PostDAO postDAO;

	@Inject
	private Messages messages;

	@InjectComponent
	private Form form;

	@InjectPage
	private ListPost listpost;

	/**
	 * Activates the page. Redirects to the initial page if the edited post is in a blog owned by
	 * another user.
	 */
	Object onActivate(EventContext context) {

		if (context.getCount() > 0) {

			Integer id = context.get(Integer.class, 0);
			post = postDAO.findById(id);

			if (post != null && post.getBlog() != null
					&& post.getBlog().getOwner().equals(user) == false) {
				return Initial.class;
			}

		}

		return null;

	}

	/**
	 * Saves or updates the post.
	 */
	@OnEvent(EventConstants.SUCCESS)
	Object saveOrUpdate() {

		postDAO.saveOrUpdate(post);
		listpost.setMessage(messages.get("message.success.saveorupdate.post"));

		return listpost;

	}

	/**
	 * Validates the stringId property.
	 */
	@OnEvent(EventConstants.VALIDATE_FORM)
	void validate() {

	}

	/**
	 * Instantiates the post if needed.
	 */
	@OnEvent(EventConstants.PREPARE)
	void prepare() {

		if (post == null) {
			post = new Post();
			post.setDate(new Date());
		}

	}

}
