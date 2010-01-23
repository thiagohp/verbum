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

import br.com.machina.verbum.dao.BlogDAO;
import br.com.machina.verbum.entities.Blog;
import br.com.machina.verbum.entities.User;

/**
 * {@link BlogDAOImpl} implementation using Hibernate.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class BlogDAOImpl implements BlogDAO {

	final private Session session;

	/**
	 * Single constructor of this class.
	 * 
	 * @param session a {@link Session}.
	 */
	public BlogDAOImpl(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<Blog> findAll() {

		final Criteria criteria = session.createCriteria(Blog.class);
		return criteria.list();
		
	}

	public void remove(Blog blog) {
		session.delete(blog);
	}

	public void saveOrUpdate(Blog blog) {
		session.saveOrUpdate(blog);
	}

	@SuppressWarnings("unchecked")
	public List<Blog> findByOwner(User owner) {
		
		final Criteria criteria = session.createCriteria(Blog.class);
		criteria.add(Restrictions.eq("owner", owner));
		
		return criteria.list();
		
	}

	public Blog findByStringId(String stringId) {
		
		final Criteria criteria = session.createCriteria(Blog.class);
		criteria.add(Restrictions.eq("stringId", stringId));
		
		return (Blog) criteria.uniqueResult();

	}

}
