package org.test.springsandbox.test.mapstruct.mappers;

import org.mapstruct.*;
import org.test.springsandbox.domain.Person;
import org.test.springsandbox.test.mapstruct.entities.Company;
import org.test.springsandbox.test.mapstruct.entities.CompanyDTO;
import org.test.springsandbox.web.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;


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
}
