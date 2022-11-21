package com.daou.kooteam.exception.user;

import com.daou.kooteam.exception.Message;
import com.daou.kooteam.exception.SwhackathonException;
import org.springframework.http.HttpStatus;

public class UserTokenNotFoundException extends SwhackathonException {
    public UserTokenNotFoundException() {
        super(HttpStatus.UNAUTHORIZED, Message.USER_TOKEN_NOT_FOUND);
    }
}
