/*
 * Copyright (c) 2017 - 2018 ICORE Software Development LLP
 * http://icode.kz
 */
package org.test.springsandbox.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter @Setter
public class VoidEvent extends ApplicationEvent {
    private String message;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public VoidEvent(Object source) {
        super(source);
        this.message = (String) source;
    }
}
