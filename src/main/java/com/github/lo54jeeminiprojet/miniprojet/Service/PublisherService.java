package com.github.lo54jeeminiprojet.miniprojet.Service;

import com.github.lo54jeeminiprojet.miniprojet.Entity.Client;
import com.github.lo54jeeminiprojet.miniprojet.Repository.PublisherDao.IPublisherDao;
import com.github.lo54jeeminiprojet.miniprojet.Repository.PublisherDao.JmsDao;
import com.github.lo54jeeminiprojet.miniprojet.Repository.PublisherDao.exceptions.PublisherDaoException;
import com.github.lo54jeeminiprojet.miniprojet.Service.exceptions.PublisherServiceException;

import javax.jms.JMSException;

public class PublisherService {

	private IPublisherDao dao;
	private int registrationTopicID;

	public PublisherService(String url) throws PublisherServiceException {
		try {
			dao = new JmsDao(url);
			registrationTopicID = dao.addTopic("LO54_JEE/PROJECT_JMS/COURSES/REGISTRATION");
		}catch(JMSException e){
			throw new PublisherServiceException("Error during initialization", e);
		}catch(PublisherDaoException e){
			throw new PublisherServiceException("Error during initialization", e);
		}
	}

	public void startService() {
		dao.startConnection();
	}

	public void stopService() {
		dao.stopConnection();
	}

	public void closeService() {
		dao.closeConnection();
	}

	public boolean publishRegistrationMessage(Client client) {
		try {
			dao.publishText(registrationTopicID, generateRegistrationMessage(client));
			return true;
		} catch (PublisherDaoException e) {
			e.printStackTrace();
			return false;
		}
	}

	private String generateRegistrationMessage(Client client){
		//TODO
		return String.format("%s %s just registered to course %s, at %s in %s", client.getLastName(), client.getFirstName(), /*TODO ajout nom cours*/, /*TODO ajout heure cours*/, /*TODO ajout lieu cours*/);
	}
}
