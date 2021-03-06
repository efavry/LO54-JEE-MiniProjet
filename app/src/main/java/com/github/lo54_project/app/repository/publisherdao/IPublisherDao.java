package com.github.lo54_project.app.repository.publisherdao;

import com.github.lo54_project.app.entity.Client;
import com.github.lo54_project.app.repository.publisherdao.exceptions.PublisherDaoException;

import java.io.Serializable;

public interface IPublisherDao
{
    public boolean publishText(String topic, String text) throws PublisherDaoException;
    public boolean startConnection();
    public boolean closeConnection();

}
