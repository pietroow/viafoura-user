package com.viafoura.user.exception.custom;

import lombok.Getter;

@Getter
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String s) {
        super(s);
    }

}
