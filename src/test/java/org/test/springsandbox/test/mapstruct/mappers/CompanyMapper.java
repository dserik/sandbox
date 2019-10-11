package org.test.springsandbox.test.mapstruct.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.test.springsandbox.domain.Person;
import org.test.springsandbox.test.mapstruct.entities.Company;
import org.test.springsandbox.test.mapstruct.entities.CompanyDTO;
import org.test.springsandbox.web.dto.PersonDTO;


@Mapper(componentModel = "cdi")
public interface CompanyMapper {

    @Mapping(target = "personal", source = "personList")
    @Mapping(target = "companyName", source = "name")
    void updateCompanyInfo(Company company, @MappingTarget CompanyDTO dto);

    @InheritInverseConfiguration(name = "updateCompanyInfo")
    void updateCompanyInfo(CompanyDTO dto, @MappingTarget Company entity);

    @Mapping(target = "personal", expression = "java(toDTO(company.getPersonList(), posName))")
    @Mapping(target = "id", source = "company.id")
    @Mapping(target = "code", source = "company.code")
    @Mapping(target = "companyName", source = "company.name")
    CompanyDTO toDTO(Company company, String posName);

    @Mapping(target = "positionName", source = "posName")
    @Mapping(target = "middleName", source = "person.middleName")
    @Mapping(target = "lastName", source = "person.lastName")
    @Mapping(target = "id", source = "person.id")
    @Mapping(target = "firstName", source = "person.firstName")
    @Mapping(target = "birthDate", source = "person.birthDate")
    PersonDTO toDTO(Person person, String posName);
}
