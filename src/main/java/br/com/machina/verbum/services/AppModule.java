package br.com.machina.verbum.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;

import br.com.machina.verbum.dao.BlogDAO;
import br.com.machina.verbum.dao.PostDAO;
import br.com.machina.verbum.dao.UserDAO;
import br.com.machina.verbum.dao.hibernate.BlogDAOImpl;
import br.com.machina.verbum.dao.hibernate.PostDAOImpl;
import br.com.machina.verbum.dao.hibernate.UserDAOImpl;
import br.com.machina.verbum.services.impl.Sha1PasswordEncrypter;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule {

	/**
	 * Adds some services by autobuilding (injection using the constructor).
	 * 
	 * @param binder a {@link ServiceBinder}.
	 */
	public static void bind(ServiceBinder binder) {
		
		binder.bind(PasswordEncrypter.class, Sha1PasswordEncrypter.class);
		binder.bind(UserDAO.class, UserDAOImpl.class);
		binder.bind(BlogDAO.class, BlogDAOImpl.class);
		binder.bind(PostDAO.class, PostDAOImpl.class);
		
	}

	/**
	 * Sets the value of Tapestry's configuration symbols.
	 * @param configuration a {@link MappedConfiguration}.
	 */
	public static void contributeApplicationDefaults(
			MappedConfiguration<String, String> configuration) {
		
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en, pt_BR");
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
		configuration.add(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
		
	}

}
