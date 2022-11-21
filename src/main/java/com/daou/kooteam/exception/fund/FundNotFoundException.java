package com.daou.kooteam.exception.fund;

import com.daou.kooteam.exception.Message;
import com.daou.kooteam.exception.SwhackathonException;
import org.springframework.http.HttpStatus;

public class FundNotFoundException extends SwhackathonException {
    public FundNotFoundException() {
        super(HttpStatus.NOT_FOUND, Message.FUND_NOT_FOUND);
    }
}
