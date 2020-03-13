package org.test.springsandbox.test.mapstruct;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.springsandbox.SpringSandboxApplication;
import org.test.springsandbox.domain.Person;
import org.test.springsandbox.test.mapstruct.entities.Company;
import org.test.springsandbox.test.mapstruct.entities.CompanyDTO;
import org.test.springsandbox.test.mapstruct.mappers.SpringCompanyMapper;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SpringSandboxApplication.class)
public class SpringMapperTest {

    @Autowired
    private SpringCompanyMapper mapper;

    @Test
    public void mappingTargetTest() {
        Company source = new Company();
        source.setName("test_name");

        CompanyDTO target = new CompanyDTO();

        mapper.updateCompanyInfo(source, target);
        Assert.assertEquals(source.getName(), target.getCompanyName());

    }

    @Test
    public void inheritInverseConfigurationTest() {
        CompanyDTO source = new CompanyDTO();
        source.setCompanyName("company name");

        Company target = new Company();
        mapper.updateCompanyInfo(source, target);

        Assert.assertEquals(source.getCompanyName(), target.getName());
    }

    @Test
    public void severalParameters() {
        Company company = new Company();
        company.setPersonList(Collections.singletonList(new Person()));

        CompanyDTO dto = mapper.toDTO(company, "manager");
        Assert.assertEquals("manager", dto.getPersonal().get(0).getPositionName());
    }
}
