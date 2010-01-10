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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class that represents a blog. Each can only have one owner and have one user posting to it. The
 * <code>stringId</code> property must be unique and is used in the blog's URL.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
@Entity
@Table(name = "blog")
public class Blog {

	private Integer id;

	private User owner;

	private String title;

	private String subtitle;

	private String stringId;

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
	 * Returns the value of the <code>owner</code> property.
	 * 
	 * @return a {@link User}.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "owner_id", nullable = false)
	@NotNull
	public User getOwner() {
		return owner;
	}

	/**
	 * Changes the value of the <code>owner</code> property.
	 * 
	 * @param owner a {@link User}.
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * Returns the value of the <code>title</code> property.
	 * 
	 * @return a {@link String}.
	 */
	@Column(nullable = false, length = 70)
	@NotNull
	@Size(min = 1, max = 70)
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
	 * Returns the value of the <code>subtitle</code> property.
	 * 
	 * @return a {@link String}.
	 */
	@Column(nullable = false, length = 250)
	@NotNull
	@Size(min = 1, max = 250)
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * Changes the value of the <code>subtitle</code> property.
	 * 
	 * @param subtitle a {@link String}.
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * Returns the value of the <code>stringId</code> property.
	 * 
	 * @return a {@link String}.
	 */
	@Column(nullable = false, length = 30)
	@NotNull
	@Size(min = 1, max = 30)
	public String getStringId() {
		return stringId;
	}

	/**
	 * Changes the value of the <code>stringId</code> property.
	 * 
	 * @param stringId a {@link String}.
	 */
	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getStringId() == null) ? 0 : getStringId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Blog == false) {
			return false;
		}
		Blog other = (Blog) obj;
		if (getStringId() == null) {
			if (other.getStringId() != null) {
				return false;
			}
		}
		else if (!getStringId().equals(other.getStringId())) {
			return false;
		}
		return true;
	}

}
