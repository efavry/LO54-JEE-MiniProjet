package com.github.lo54_project.app.util;

import com.github.lo54_project.app.entity.Client;
import com.github.lo54_project.app.entity.Course;
import com.github.lo54_project.app.entity.CourseSession;
import com.github.lo54_project.app.entity.Location;
import org.hibernate.HibernateException;
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
			return new AnnotationConfiguration().configure().buildSessionFactory();
		}
		catch (ClassFormatError ex)
		{
			System.err.println("Initial SessionFactory creation failed." + ex);
			Log.error(HibernateUtil.class,"Initial SessionFactory creation failed." + ex);
			//throw new ExceptionInInitializerError(ex);
			return null;
		}

	}

	public static boolean persist(Session session, Object o)
	{
		try
		{
			session.beginTransaction();
			session.persist(o);
			session.getTransaction().commit();
			return true;
		}
		catch (HibernateException hex)
		{
			session.getTransaction().rollback();
			hex.printStackTrace();
			return false;
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
