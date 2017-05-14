package com.github.lo54jeeminiprojet.miniprojet.Repository.PublisherDao.exceptions;

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
