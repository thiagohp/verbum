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

package br.com.machina.verbum;

import java.util.Scanner;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import br.com.machina.verbum.entities.User;
import br.com.machina.verbum.services.impl.Sha1PasswordEncrypter;

/**
 * Class that runs Verbum in an embedded Jetty instance.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class Main {

	/**
	 * Runs Verbum in Jetty.
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		
//		addAdministratorUser();
		
		Server server = new Server(8080);

		WebAppContext webapp = new WebAppContext();

		webapp.setParentLoaderPriority(true);
		webapp.setContextPath("/");
		webapp.setWar("src/main/webapp");
		// webapp.setDefaultsDescriptor("src/main/webapp/WEB-INF/webdefault.xml");

		server.setHandler(webapp);

		try {
			server.start();
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Type Enter to stop your application.");
			scanner.nextLine();
			server.stop();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void generateTables() {
		
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();
		
		SchemaExport export = new SchemaExport(configuration);
		export.setDelimiter(";");
		export.setOutputFile("tables.sql");
		export.create(true, true);
		System.out.println("Ok!");
		
	}
	
	private static void addAdministratorUser() {
		
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			User user = new User();
			user.setName(ask("Please type your name:"));
			user.setLogin(ask("Please type your login:"));
			final String password = ask("Please type your password:");
			user.setEmail(ask("Please type your e-mail:"));
			
			Sha1PasswordEncrypter encrypter = new Sha1PasswordEncrypter();
			user.setPassword(encrypter.encrypt(password));
			
			user.setAdministrator(true);
			
			session.save(user);
			
			transaction.commit();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		finally {
		
			if (session != null) {
				session.close();
			}
			
			sessionFactory.close();
			
		}
		
	}
	
	final private static String ask(String message) {
		return JOptionPane.showInputDialog(message);
	}

}
