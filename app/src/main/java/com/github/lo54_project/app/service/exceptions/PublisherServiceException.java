package com.github.lo54_project.app.service.exceptions;


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
