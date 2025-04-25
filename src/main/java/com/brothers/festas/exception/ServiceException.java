package com.brothers.festas.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{

    private final String message;

    private final String technicalMessage;

    public ServiceException(String message) {
        this.message =  message;
        this.technicalMessage = null;
    }

    public ServiceException(String message, String technicalMessage) {
        this.message =  message;
        this.technicalMessage = technicalMessage;
    }
}
