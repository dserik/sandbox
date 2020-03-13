/*
 * Copyright (c) 2017 - 2018 ICORE Software Development LLP
 * http://icode.kz
 */
package org.test.springsandbox.test.mapstruct.mappers;

import org.mapstruct.*;
import org.test.springsandbox.domain.Person;
import org.test.springsandbox.test.mapstruct.entities.Company;
import org.test.springsandbox.test.mapstruct.entities.CompanyDTO;
import org.test.springsandbox.test.mapstruct.entities.FirstImpl;
import org.test.springsandbox.test.mapstruct.entities.SomeDTO;
import org.test.springsandbox.test.mapstruct.resolvers.DictionaryResolver;
import org.test.springsandbox.web.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring", uses = DictionaryResolver.class)
public interface SpringCompanyMapper {

    @Mapping(target = "personal", source = "personList")
    @Mapping(target = "companyName", source = "name")
    void updateCompanyInfo(Company company, @MappingTarget CompanyDTO dto);

    @InheritInverseConfiguration(name = "updateCompanyInfo")
    void updateCompanyInfo(CompanyDTO dto, @MappingTarget Company entity);

    FirstImpl map(SomeDTO dto);

    @Mapping(target = "sampleField", source = "company.sampleField")
    @Mapping(target = "personal", expression = "java(toDTO(company.getPersonList(), posName))")
    @Mapping(target = "id", source = "company.id")
    @Mapping(target = "code", source = "company.code", resultType = String.class)
    @Mapping(target = "companyName", source = "company.name")
    CompanyDTO toDTO(Company company, String posName);

    // If set returning type as List<PersonDTO> throws:
    // The return type List<PersonDTO> is an abstract class or interface.
    // Provide a non abstract / non interface result type or a factory method.
    ArrayList<PersonDTO> toDTO(List<Person> personList, String posName);

    @AfterMapping
    default void afterMapping(List<Person> sourcePersonal, String posName,
                              @MappingTarget List<PersonDTO> targetPersonal) {
        toDTO(targetPersonal, sourcePersonal);
        targetPersonal.forEach(personDTO -> personDTO.setPositionName(posName));
    }

    void toDTO(@MappingTarget List<PersonDTO> dtos, List<Person> entities);

    @Mapping(target = "positionName", ignore = true)
    PersonDTO toDTO(Person person);

    SomeDTO map(FirstImpl entity);
}
