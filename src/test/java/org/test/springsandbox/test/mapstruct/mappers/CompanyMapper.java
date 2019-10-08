/*
 * Copyright (c) 2017 - 2018 ICORE Software Development LLP
 * http://icode.kz
 */
package org.test.springsandbox.test.mapstruct.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.test.springsandbox.test.mapstruct.entities.Company;
import org.test.springsandbox.test.mapstruct.entities.CompanyDTO;

@Mapper
public interface CompanyMapper {

    @Mapping(target = "companyName", source = "name")
    void updateCompanyInfo(Company company, @MappingTarget CompanyDTO dto);
}
