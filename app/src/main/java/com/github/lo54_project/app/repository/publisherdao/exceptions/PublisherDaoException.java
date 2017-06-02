package com.github.lo54_project.app.repository.publisherdao.exceptions;

/**
 * Created by Notmoo on 14/05/2017.
 */
public class PublisherDaoException extends Exception {

    public PublisherDaoException(String message){
        super(message);
    }
    public PublisherDaoException(Throwable cause){
        super(cause);
    }
    public PublisherDaoException(String message, Throwable cause){
        super(message, cause);
    }
}
