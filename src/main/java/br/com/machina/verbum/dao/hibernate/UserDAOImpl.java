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

package br.com.machina.verbum.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.machina.verbum.dao.UserDAO;
import br.com.machina.verbum.entities.User;
import br.com.machina.verbum.services.PasswordEncrypter;

/**
 * {@link UserDAOImpl} implementation using Hibernate.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class UserDAOImpl implements UserDAO {

	final private Session session;
	
	final private PasswordEncrypter passwordEncrypter;

	/**
	 * Single constructor of this class.
	 * 
	 * @param session a {@link Session}.
	 * @param passwordEncrypter a {@link PasswordEncrypter}.
	 */
	public UserDAOImpl(Session session, PasswordEncrypter passwordEncrypter) {
		
		this.session = session;
		this.passwordEncrypter = passwordEncrypter;
		
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		
		final Criteria criteria = session.createCriteria(User.class);
		return criteria.list();
	}

	public User findByLogin(String login) {
		
		final Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));
		
		return (User) criteria.uniqueResult();
		
	}

	public User findByLoginAnsPassword(String login, String password) {
		
		final String encryptedPassword = passwordEncrypter.encrypt(password);
		final Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("password", encryptedPassword));
		
		return (User) criteria.uniqueResult();

	}

	public void remove(User user) {
		session.delete(user);
	}

	public void saveOrUpdate(User user) {

		user.setPassword(passwordEncrypter.encrypt(user.getPassword()));
		session.saveOrUpdate(user);
		
	}

}
