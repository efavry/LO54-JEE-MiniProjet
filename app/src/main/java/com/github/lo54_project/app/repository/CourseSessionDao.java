package com.github.lo54_project.app.repository;

import com.github.lo54_project.app.entity.CourseSession;
import com.github.lo54_project.app.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CourseSessionDao implements IRepoDao<CourseSession>
{

	private Session session;
	public CourseSessionDao()
	{
		session = HibernateUtil.getSessionFactory().openSession(); // no try catchy to be sure that it is catched upper in the stack call

	}

	public CourseSessionDao (Session session)
	{
		this.session = session;
	}

	public void close()
	{
		if(session != null)
		{
			try
			{
				session.close();
			}
			catch(HibernateException hex)
			{
				hex.printStackTrace();
			}
		}
	}

	public boolean save(CourseSession o)
	{
		return HibernateUtil.persist(session,o);
	}

	public CourseSession get(long id)
	{
		try
		{
			session.beginTransaction();
			CourseSession courseSession = (CourseSession) session.get(CourseSession.class, id);
			session.getTransaction().commit();
			return courseSession;
		}
		catch (HibernateException hex)
		{
			hex.printStackTrace();
		}
		return null;
	}

	@Override
	public Session getOpenedSession()
	{
		return session;
	}
}
