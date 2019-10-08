/*
 * Copyright (c) 2017 - 2018 ICORE Software Development LLP
 * http://icode.kz
 */
package org.test.springsandbox.test.mapstruct.entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Company {
    private Long id;
    private String name;
    private String code;
}
