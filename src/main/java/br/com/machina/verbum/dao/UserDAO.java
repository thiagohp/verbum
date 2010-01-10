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

package br.com.machina.verbum.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import br.com.machina.verbum.entities.User;

/**
 * Hibernate DAO implementation for {@link DAO}.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public interface UserDAO {

	/**
	 * Inserts or updates an user.
	 * 
	 * @param user an {@link User}.
	 */
	@CommitAfter
	void saveOrUpdate(User user);

	/**
	 * Returns all users.
	 * 
	 * @return a {@link List} of {@link User}s.
	 */
	List<User> findAll();

	/**
	 * Removes an user.
	 * 
	 * @param user an {@link User}.
	 */
	@CommitAfter
	void remove(User user);

	/**
	 * Finds an user by its login.
	 * 
	 * @return an {@link User} or <code>null</code>.
	 */
	User findByLogin(String login);

	/**
	 * Finds an user by its login and password.
	 * 
	 * @param login a {@link String}.
	 * @param password a {@link String}.
	 * @return an {@link User} or <code>null</code>.
	 */
	User findByLoginAnsPassword(String login, String password);

}
