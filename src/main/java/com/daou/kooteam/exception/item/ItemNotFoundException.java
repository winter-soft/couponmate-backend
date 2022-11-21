package com.daou.kooteam.exception.item;

import com.daou.kooteam.exception.Message;
import com.daou.kooteam.exception.SwhackathonException;
import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends SwhackathonException {
    public ItemNotFoundException() {
        super(HttpStatus.NOT_FOUND, Message.ITEM_NOT_FOUND);
    }
}
