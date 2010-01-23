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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.machina.verbum.dao.PostDAO;
import br.com.machina.verbum.entities.Blog;
import br.com.machina.verbum.entities.Post;

/**
 * {@link PostDAOImpl} implementation using Hibernate.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class PostDAOImpl implements PostDAO {

	final private Session session;

	/**
	 * Single constructor of this class.
	 * 
	 * @param session a {@link Session}.
	 */
	public PostDAOImpl(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<Post> findAll() {

		final Criteria criteria = session.createCriteria(Post.class);
		criteria.add(Restrictions.eq("draft", false));
		criteria.addOrder(Order.desc("date"));
		
		return criteria.list();
		
	}

	public void remove(Post post) {
		session.delete(post);
	}

	public void saveOrUpdate(Post post) {
		session.saveOrUpdate(post);
	}

	@SuppressWarnings("unchecked")
	public List<Post> findByBlog(Blog blog) {
		
		final Criteria criteria = session.createCriteria(Post.class);
		criteria.add(Restrictions.eq("blog", blog));
		
		return criteria.list();
		
	}

	public Post findById(int id) {
		
		final Criteria criteria = session.createCriteria(Post.class);
		criteria.add(Restrictions.eq("id", id));
		
		return (Post) criteria.list();
		
	}

	@SuppressWarnings("unchecked")
	public List<Post> findLatest(int maximum) {

		final Criteria criteria = session.createCriteria(Post.class);
		criteria.add(Restrictions.eq("draft", false));
		criteria.addOrder(Order.desc("date"));
		criteria.setMaxResults(maximum);
		
		return criteria.list();
		
	}

}
