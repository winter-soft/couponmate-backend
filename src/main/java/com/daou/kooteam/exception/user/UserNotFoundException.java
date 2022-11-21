package com.daou.kooteam.exception.user;

import com.daou.kooteam.exception.Message;
import com.daou.kooteam.exception.SwhackathonException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends SwhackathonException {
    public UserNotFoundException() {
        super(HttpStatus.BAD_REQUEST, Message.USER_NOT_FOUND);
    }
}
