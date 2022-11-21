package com.daou.kooteam.exception.user;

import com.daou.kooteam.exception.Message;
import com.daou.kooteam.exception.SwhackathonException;
import org.springframework.http.HttpStatus;

public class UserDuplicatedException extends SwhackathonException {

    public UserDuplicatedException() {
        super(HttpStatus.BAD_REQUEST, Message.USER_DUPLICATED);
    }
}
