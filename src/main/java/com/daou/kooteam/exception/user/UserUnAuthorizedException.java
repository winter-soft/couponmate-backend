package com.daou.kooteam.exception.user;

import com.daou.kooteam.exception.Message;
import com.daou.kooteam.exception.SwhackathonException;
import org.springframework.http.HttpStatus;

public class UserUnAuthorizedException extends SwhackathonException {

    public UserUnAuthorizedException() {
        super(HttpStatus.UNAUTHORIZED, Message.USER_UNAUTHORIZED);
    }
}
