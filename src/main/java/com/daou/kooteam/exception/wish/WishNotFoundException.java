package com.daou.kooteam.exception.wish;

import com.daou.kooteam.exception.Message;
import com.daou.kooteam.exception.SwhackathonException;
import org.springframework.http.HttpStatus;

public class WishNotFoundException extends SwhackathonException {
    public WishNotFoundException() {
        super(HttpStatus.NOT_FOUND, Message.WISH_NOT_FOUND);
    }
}
