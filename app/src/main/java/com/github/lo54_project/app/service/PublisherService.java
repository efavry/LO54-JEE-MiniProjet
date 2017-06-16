package com.github.lo54_project.app.service;

import com.github.lo54_project.app.entity.Client;
import com.github.lo54_project.app.repository.publisherdao.IPublisherDao;
import com.github.lo54_project.app.repository.publisherdao.JmsDao;
import com.github.lo54_project.app.repository.publisherdao.exceptions.PublisherDaoException;
import com.github.lo54_project.app.service.exceptions.PublisherServiceException;

import javax.jms.JMSException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PublisherService {

	private IPublisherDao dao;
	private String registrationTopic;

	public PublisherService(String url) throws PublisherServiceException {
		try {
			dao = new JmsDao(url);
			registrationTopic = "topic";
		}catch(JMSException e){
			throw new PublisherServiceException("Error during initialization", e);
		}
	}

	public boolean startService() {
		return dao.startConnection();
	}

	public boolean closeService() {
		return dao.closeConnection();
	}

	public boolean publishRegistrationMessage(Client client) {
		try {
			dao.publishText(registrationTopic, generateRegistrationMessage(client));
			return true;
		} catch (PublisherDaoException e) {
			e.printStackTrace();
			return false;
		}
	}

	//ça me semble bien
	private String generateRegistrationMessage(Client client){
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, HH:mm");
		return String.format("%s %s just registered to course %s, at %s in %s", client.getLastName(), client.getFirstName(), client.getCourseSession().getCourse().getTitle(), dateFormat.format(client.getCourseSession().getStartDate()), client.getCourseSession().getLocation().getCity());
	}
}
