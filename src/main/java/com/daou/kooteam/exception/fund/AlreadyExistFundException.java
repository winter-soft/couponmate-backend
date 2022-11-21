package com.daou.kooteam.exception.fund;

import com.daou.kooteam.exception.Message;
import com.daou.kooteam.exception.SwhackathonException;
import org.springframework.http.HttpStatus;

public class AlreadyExistFundException extends SwhackathonException {
    public AlreadyExistFundException() {
        super(HttpStatus.BAD_REQUEST, Message.ALREADY_EXIST_FUND);
    }
}
