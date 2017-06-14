package com.github.lo54_project.app.repository;

import com.github.lo54_project.app.entity.Location;
import com.github.lo54_project.app.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class LocationDao implements IRepoDao<Location>
{

	private Session session;
	public LocationDao()
	{
		session = HibernateUtil.getSessionFactory().openSession(); // no try catchy to be sure that it is catched upper in the stack call

	}

	public LocationDao(Session session)
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

	@Override
	public boolean save(Location location)
	{
		return HibernateUtil.persist(session,location);
	}

	public Location get(long id)
	{
		try
		{
			session.beginTransaction();
			Location courseSession = (Location) session.get(Location.class, id);
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
