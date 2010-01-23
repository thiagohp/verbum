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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class that represents
 * 
 * @author Thiago H. de Paula Figueiredo
 */
@Entity
@Table(name = "post")
public class Post {

	private Integer id;

	private Date date;

	private String title;

	private String content;

	private boolean commentsAllowed;

	private boolean draft;

	private Blog blog;

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
	 * Returns the value of the <code>date</code> property.
	 * 
	 * @return a {@link Date}.
	 */
	@Column(nullable = false)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	/**
	 * Changes the value of the <code>date</code> property.
	 * 
	 * @param date a {@link Date}.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Returns the value of the <code>content</code> property.
	 * 
	 * @return a {@link String}.
	 */
	@Lob
	@NotNull
	@Size(min = 1)
	public String getContent() {
		return content;
	}

	/**
	 * Changes the value of the <code>content</code> property.
	 * 
	 * @param content a {@link String}.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Returns the value of the <code>commentsAllowed</code> property.
	 * 
	 * @return a {@link boolean}.
	 */
	public boolean isCommentsAllowed() {
		return commentsAllowed;
	}

	/**
	 * Changes the value of the <code>commentsAllowed</code> property.
	 * 
	 * @param commentsAllowed a {@link boolean}.
	 */
	public void setCommentsAllowed(boolean commentsAllowed) {
		this.commentsAllowed = commentsAllowed;
	}

	/**
	 * Returns the value of the <code>blog</code> property.
	 * 
	 * @return a {@link Blog}.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "blog_id", nullable = false)
	@NotNull
	public Blog getBlog() {
		return blog;
	}

	/**
	 * Changes the value of the <code>blog</code> property.
	 * 
	 * @param blog a {@link Blog}.
	 */
	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	/**
	 * Returns the value of the <code>draft</code> property.
	 * 
	 * @return a {@link boolean}.
	 */
	public boolean isDraft() {
		return draft;
	}

	/**
	 * Changes the value of the <code>draft</code> property.
	 * 
	 * @param draft a {@link boolean}.
	 */
	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	/**
	 * Returns the value of the <code>title</code> property.
	 * 
	 * @return a {@link String}.
	 */
	@Column(nullable = false, length = 150)
	@Length(min = 1, max = 150)
	@NotEmpty
	public String getTitle() {
		return title;
	}

	/**
	 * Changes the value of the <code>title</code> property.
	 * 
	 * @param title a {@link String}.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		if (obj instanceof Post == false) {
			return false;
		}
		Post other = (Post) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		}
		else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

}
