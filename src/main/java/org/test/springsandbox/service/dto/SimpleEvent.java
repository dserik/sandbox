/*
 * Copyright (c) 2017 - 2018 ICORE Software Development LLP
 * http://icode.kz
 */
package org.test.springsandbox.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class SimpleEvent {
    private final String message;
}
