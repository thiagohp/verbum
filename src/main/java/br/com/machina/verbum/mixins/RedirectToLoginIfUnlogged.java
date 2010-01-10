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

package br.com.machina.verbum.mixins;

import org.apache.tapestry5.annotations.SessionState;

import br.com.machina.verbum.entities.User;
import br.com.machina.verbum.pages.Login;

/**
 * Mixin that redirects to the login page if the user is not logged in.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class RedirectToLoginIfUnlogged {

	@SessionState(create = false)
	private User user;

	/**
	 * Redirects to the login page if the user is not logged in.
	 */
	Object onActivate() {

		if (user == null) {
			// redirect to the login page.
			return Login.class;
		}
		else {
			// let the page render normally
			return null;
		}
		
	}
	
}
