/*
 * Copyright (c) 2017 - 2018 ICORE Software Development LLP
 * http://icode.kz
 */
package org.test.springsandbox.test.mapstruct.entities;

import lombok.Getter;
import lombok.Setter;
import org.test.springsandbox.web.dto.PersonDTO;

@Getter @Setter
public class CompanyDTO {
    private Long id;
    private String companyName;
    private String code;
    private PersonDTO personal;
}
