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

package br.com.machina.verbum.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class that represents an user.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
@Entity
@Table(name = "user")
public class User {

	private Integer id;

	private String login;

	private String password;

	private String name;

	private String email;

	private boolean administrator;

	/**
	 * Returns the value of the <code>id</code> property.
	 * 
	 * @return a {@link Integer}.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	/**
	 * Changes the value of the <code>id</code> property.
	 * 
	 * @param id a {@link Integer}.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Returns the value of the <code>login</code> property.
	 * 
	 * @return a {@link String}.
	 */
	@Column(nullable = false, length = 30)
	@NotNull
	@Size(min = 1, max = 30)
	public String getLogin() {
		return login;
	}

	/**
	 * Changes the value of the <code>login</code> property.
	 * 
	 * @param login a {@link String}.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Returns the value of the <code>password</code> property.
	 * 
	 * @return a {@link String}.
	 */
	@Column(nullable = false, length = 40)
	@NotNull
	@Size(min = 6, max = 40)
	public String getPassword() {
		return password;
	}

	/**
	 * Changes the value of the <code>password</code> property.
	 * 
	 * @param password a {@link String}.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the value of the <code>name</code> property.
	 * 
	 * @return a {@link String}.
	 */
	@Column(nullable = false, length = 50)
	@NotNull
	@Size(min = 1, max = 50)
	public String getName() {
		return name;
	}

	/**
	 * Changes the value of the <code>name</code> property.
	 * 
	 * @param name a {@link String}.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the value of the <code>email</code> property.
	 * 
	 * @return a {@link String}.
	 */
	@Column(nullable = false, length = 50)
	@NotNull
	@Size(min = 3, max = 50)
	public String getEmail() {
		return email;
	}

	/**
	 * Changes the value of the <code>email</code> property.
	 * 
	 * @param email a {@link String}.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the value of the <code>administrator</code> property.
	 * 
	 * @return a {@link boolean}.
	 */
	public boolean isAdministrator() {
		return administrator;
	}

	/**
	 * Changes the value of the <code>administrator</code> property.
	 * 
	 * @param administrator a {@link boolean}.
	 */
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof User == false) {
			return false;
		}
		User other = (User) obj;
		if (getLogin() == null) {
			if (other.getLogin() != null) {
				return false;
			}
		}
		else if (!getLogin().equals(other.getLogin())) {
			return false;
		}
		return true;
	}

}
