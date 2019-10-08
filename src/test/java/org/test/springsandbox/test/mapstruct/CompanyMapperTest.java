/*
 * Copyright (c) 2017 - 2018 ICORE Software Development LLP
 * http://icode.kz
 */
package org.test.springsandbox.test.mapstruct;

import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.test.springsandbox.test.mapstruct.entities.Company;
import org.test.springsandbox.test.mapstruct.entities.CompanyDTO;
import org.test.springsandbox.test.mapstruct.mappers.CompanyMapper;

public class CompanyMapperTest {

    @Test
    public void mappingTargetTest() {
        CompanyMapper mapper = Mappers.getMapper(CompanyMapper.class);

        Company source = new Company();
        source.setName("test_name");

        CompanyDTO target = new CompanyDTO();

        mapper.updateCompanyInfo(source, target);
        Assert.assertEquals(source.getName(), target.getCompanyName());
    }
}
