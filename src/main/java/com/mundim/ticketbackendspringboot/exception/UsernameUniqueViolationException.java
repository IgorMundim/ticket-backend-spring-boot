package com.mundim.ticketbackendspringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UsernameUniqueViolationException extends RuntimeException{
    public UsernameUniqueViolationException(String message){
        super(message);
    }

}
