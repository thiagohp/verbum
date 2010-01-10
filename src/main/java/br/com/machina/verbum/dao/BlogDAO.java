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

import br.com.machina.verbum.entities.Blog;
import br.com.machina.verbum.entities.User;

/**
 * Hibernate DAO implementation for {@link Blog}.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public interface BlogDAO {

	/**
	 * Inserts or updates an blog.
	 * 
	 * @param blog an {@link Blog}.
	 */
	@CommitAfter
	void saveOrUpdate(Blog blog);

	/**
	 * Returns all blogs.
	 * 
	 * @return a {@link List} of {@link Blog}s.
	 */
	List<Blog> findAll();
	
	/**
	 * Returns all blogs owned by a given owner
	 * 
	 * @param owner an {@link User}.
	 * @return a {@link List} of {@link Blog}s.
	 */
	List<Blog> findByOwner(User owner);

	/**
	 * Removes an blog.
	 * 
	 * @param blog an {@link Blog}.
	 */
	@CommitAfter
	void remove(Blog blog);

}
