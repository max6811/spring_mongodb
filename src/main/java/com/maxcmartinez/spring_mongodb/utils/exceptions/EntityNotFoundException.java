package com.maxcmartinez.spring_mongodb.utils.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String type, String id) {
        super(String.format("%s with id:%s not found", type, id));
    }
}
