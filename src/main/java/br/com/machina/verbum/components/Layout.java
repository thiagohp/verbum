package br.com.machina.verbum.components;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;

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
	private String pageName;

	@Property
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String sidebarTitle;

	@Property
	@Parameter(defaultPrefix = BindingConstants.BLOCK, value="block:defaultHeader")
	private Block header;
	
	@Inject
	private Block defaultHeader;

	@Inject
	private ComponentResources resources;
	
	@Inject
	private Messages messages;
	
	public String getClassForPageName() {
		return resources.getPageName().equalsIgnoreCase(pageName) ? "current_page_item" : null;
	}

	public String[] getPageNames() {
		return new String[] { "Index", "About", "Contact" };
	}

	/**
	 * Returns the default value for the title parameter. 
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
