package com.github.lo54_project.core.Service.exceptions;

/**
 * Created by Notmoo on 14/05/2017.
 */
public class PublisherServiceException extends Exception {

    public PublisherServiceException(String message){
        super(message);
    }
    public PublisherServiceException(Throwable cause){
        super(cause);
    }
    public PublisherServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
