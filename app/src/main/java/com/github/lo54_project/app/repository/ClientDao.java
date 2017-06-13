package com.github.lo54_project.app.repository;

import com.github.lo54_project.app.entity.Client;
import com.github.lo54_project.app.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ClientDao implements IRepoDao<Client>
{

	private Session session;
	public ClientDao()
	{
		session = HibernateUtil.getSessionFactory().openSession(); // no try catchy to be sure that it is catched upper in the stack call

	}

	public ClientDao(Session session)
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
	public boolean save(Client m)
	{
		return HibernateUtil.persist(session,m);

	}

	public Client get(long id)
	{
		try
		{
			session.beginTransaction();
			Client client = (Client) session.get(Client.class, id);
			session.getTransaction().commit();
			return client;
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
