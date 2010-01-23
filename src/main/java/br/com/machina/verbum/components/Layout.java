package br.com.machina.verbum.components;

import java.util.List;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import br.com.machina.verbum.dao.PostDAO;
import br.com.machina.verbum.entities.Post;

/**
 * Layout component for pages of application verbum.
 */
public class Layout {

	/** The page title, for the <title> element and the <h1>element. */
	@Property
	@Parameter(required = true, defaultPrefix = BindingConstants.MESSAGE)
	@SuppressWarnings("unused")
	private String title;

	@Property
	@SuppressWarnings("unused")
	private Post post;

	@Inject
	private ComponentResources resources;

	@Inject
	private PostDAO postDAO;

	@Inject
	private Messages messages;

	/**
	 * Returns all posts from all blogs.
	 * 
	 * @return a {@link List} of {@link Post}s.
	 */
	public List<Post> getPosts() {
		return postDAO.findAll();
	}

	/**
	 * Returns the default value for the title parameter.
	 * 
	 * @return a {@link String}.
	 */
	String defaultTitle() {

		String key = "page.title." + resources.getPageName().replace('/', '.');

		if (messages.contains(key)) {
			return messages.get(key);
		}
		else {
			return null;
		}

	}

}
