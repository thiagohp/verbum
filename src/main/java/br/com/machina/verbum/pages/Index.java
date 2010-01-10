package br.com.machina.verbum.pages;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import br.com.machina.verbum.entities.User;

/**
 * Start page of application verbum.
 */
public class Index
{
	@Inject
	private Session session;
	
	public List<User> getUsers() {
		return session.createCriteria(User.class).list();
	}
	
	public Date getCurrentTime() 
	{ 
		return new Date(); 
	}
}
