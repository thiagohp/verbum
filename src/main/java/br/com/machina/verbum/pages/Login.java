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

package br.com.machina.verbum.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import br.com.machina.verbum.dao.UserDAO;
import br.com.machina.verbum.entities.User;

/**
 * Login page.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class Login {

	@Property
	private String login;

	@Property
	private String password;

	@SessionState(create = false)
	@SuppressWarnings("unused")
	private User loggedUser;

	@Inject
	private UserDAO userDAO;

	@InjectComponent
	private Form form;

	@Inject
	private Messages messages;

	/**
	 * Handles the form submission.
	 */
	@OnEvent(EventConstants.SUCCESS)
	Object login() {

		User user = userDAO.findByLoginAnsPassword(login, password);

		if (user == null) {
			form.recordError(messages.get("error.login.password.invalid"));
			return null;
		}
		else {
			loggedUser = user;
			return Initial.class;
		}

	}

}
