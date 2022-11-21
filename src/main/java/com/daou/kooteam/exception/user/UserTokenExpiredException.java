package com.daou.kooteam.exception.user;

import com.daou.kooteam.exception.Message;
import com.daou.kooteam.exception.SwhackathonException;
import org.springframework.http.HttpStatus;

public class UserTokenExpiredException extends SwhackathonException {

    public UserTokenExpiredException() {
        super(HttpStatus.UNAUTHORIZED, Message.USER_TOKEN_EXPIRED);
    }
}
