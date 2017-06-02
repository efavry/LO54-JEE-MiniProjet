package com.github.lo54_project.app.util;

import com.github.lo54_project.app.entity.Client;
import com.github.lo54_project.app.entity.Course;
import com.github.lo54_project.app.entity.CourseSession;
import com.github.lo54_project.app.entity.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory()
	{
		try
		{
		// Create the SessionFactory from hibernate.cfg.xml
			//return new Configuration().configure().buildSessionFactory();

			//Create the SessionFactory from annotation
			return new AnnotationConfiguration()
					.addPackage("com.github.lo54jeeminiprojet.miniprojet.entity")
					.addAnnotatedClass(Client.class)
					.addAnnotatedClass(Course.class)
					.addAnnotatedClass(CourseSession.class)
					.addAnnotatedClass(Location.class)
					.configure()
					.buildSessionFactory();
		}
        catch (ClassFormatError ex)
		{
			System.err.println("Initial SessionFactory creation failed." + ex);
			Log.error(HibernateUtil.class,"Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public static Session getSession()
	{
		return sessionFactory.openSession();
	}

}
