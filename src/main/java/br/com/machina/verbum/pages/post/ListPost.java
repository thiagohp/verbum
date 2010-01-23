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

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

/**
 * Page that lists blogs.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class ListPost {

	@Persist(PersistenceConstants.FLASH)
	private String message;

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
