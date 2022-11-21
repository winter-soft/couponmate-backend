package com.daou.kooteam.exception.follow;

import com.daou.kooteam.exception.Message;
import com.daou.kooteam.exception.SwhackathonException;
import org.springframework.http.HttpStatus;

public class FollowerNotFoundException extends SwhackathonException {
    public FollowerNotFoundException() {
        super(HttpStatus.NOT_FOUND, Message.FOLLOWER_NOT_FOUND);
    }
}
