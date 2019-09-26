package org.test.springsandbox.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.test.springsandbox.domain.Person;
import org.test.springsandbox.service.mapper.resolver.PersonResolver;
import org.test.springsandbox.web.dto.PersonDTO;

import java.util.List;

@SuppressWarnings({"unused"})
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {PersonResolver.class})
public abstract class PersonMapper {

    public abstract PersonDTO toDto(Person entity);

    public abstract Person toEntity(PersonDTO dto);

    public abstract List<PersonDTO> toDto(List<Person> entities);

}
