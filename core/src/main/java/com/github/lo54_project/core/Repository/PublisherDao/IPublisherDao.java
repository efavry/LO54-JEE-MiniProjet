package com.github.lo54jeeminiprojet.miniprojet.Repository.PublisherDao;

import com.github.lo54jeeminiprojet.miniprojet.Entity.Client;
import com.github.lo54jeeminiprojet.miniprojet.Repository.PublisherDao.exceptions.PublisherDaoException;

import java.io.Serializable;

public interface IPublisherDao
{

    public int addTopic(String title) throws IndexOutOfBoundsException, PublisherDaoException;
    public boolean removeTopic(int topicID) throws PublisherDaoException;
    public boolean publishText(int topicID, String text) throws PublisherDaoException;
    public boolean publishObject(int topicID, Serializable obj) throws PublisherDaoException;
    public boolean startConnection();
    public boolean stopConnection();
    public boolean closeConnection();

}