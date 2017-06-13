package com.github.lo54_project.app.service;

import com.github.lo54_project.app.entity.Client;
import com.github.lo54_project.app.repository.ClientDao;
import com.github.lo54_project.app.repository.CourseSessionDao;

public class ClientService
{

	private ClientDao dao;

	public ClientService()
	{
		this.dao = new ClientDao();
	}

	public void createClient(String firstName, String lastName, String address, String phoneNumber, String email, long sessionId)
	{
		CourseSessionDao courseSessionDao = new CourseSessionDao(dao.getOpenedSession());
		Client client = new Client(firstName,lastName,address,phoneNumber,email,courseSessionDao.get(sessionId));
		dao.save(client);
	}

}
