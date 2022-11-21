package com.daou.kooteam.exception.order;

import com.daou.kooteam.exception.Message;
import com.daou.kooteam.exception.SwhackathonException;
import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends SwhackathonException {
    public OrderNotFoundException() {
        super(HttpStatus.NOT_FOUND, Message.ORDER_NOT_FOUND);
    }
}
