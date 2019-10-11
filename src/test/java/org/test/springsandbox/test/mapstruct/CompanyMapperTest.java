package org.test.springsandbox.test.mapstruct;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.test.springsandbox.domain.Person;
import org.test.springsandbox.test.mapstruct.entities.Company;
import org.test.springsandbox.test.mapstruct.entities.CompanyDTO;
import org.test.springsandbox.test.mapstruct.mappers.CompanyMapperImpl;

import javax.inject.Inject;


@RunWith(CdiRunner.class)
public class CompanyMapperTest {

    @Inject
    private CompanyMapperImpl mapper;

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
        company.setPersonList(new Person());

        CompanyDTO dto = mapper.toDTO(company, "manager");
        Assert.assertEquals("manager", dto.getPersonal().getPositionName());
    }
}
