package com.github.lo54jeeminiprojet.miniprojet.Service;

import com.github.lo54jeeminiprojet.miniprojet.Entity.Client;
import com.github.lo54jeeminiprojet.miniprojet.Repository.PublisherDao.IPublisherDao;
import com.github.lo54jeeminiprojet.miniprojet.Repository.PublisherDao.JmsDao;
import com.github.lo54jeeminiprojet.miniprojet.Repository.PublisherDao.exceptions.PublisherDaoException;
import com.github.lo54jeeminiprojet.miniprojet.Service.exceptions.PublisherServiceException;

import javax.jms.JMSException;

public class PublisherService {

	private IPublisherDao dao;
	private int registreationTopicID;

	public PublisherService(String url) throws PublisherServiceException {
		try {
			dao = new JmsDao(url);
			registreationTopicID = dao.addTopic("LO54_JEE-JMS_PROJECT");
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
			dao.publishText(registreationTopicID, generateRegistrationMessage(client));
			return true;
		} catch (PublisherDaoException e) {
			e.printStackTrace();
			return false;
		}
	}

	private String generateRegistrationMessage(Client client){
		//TODO
		return null;
	}
}
