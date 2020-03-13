package org.test.springsandbox.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.test.springsandbox.domain.Clothes;
import org.test.springsandbox.domain.Pants;
import org.test.springsandbox.domain.Person;
import org.test.springsandbox.domain.Shirt;
import org.test.springsandbox.service.mapper.resolver.PersonResolver;
import org.test.springsandbox.web.dto.ClothesDTO;
import org.test.springsandbox.web.dto.PersonDTO;

import java.util.List;

@SuppressWarnings({"unused"})
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {PersonResolver.class})
public abstract class PersonMapper {

    @Mapping(target = "positionName", ignore = true)
    public abstract PersonDTO toDto(Person entity);

    @Mapping(target = "fullName", ignore = true)
    public abstract Person toEntity(PersonDTO dto);

    public abstract List<PersonDTO> toDto(List<Person> entities);

    ClothesDTO map(Clothes entity) {
        if (entity instanceof Pants) {
            return new ClothesDTO().setId(entity.getId()).setSize(((Pants) entity).getSize());
        } else {
            return new ClothesDTO().setId(entity.getId()).setColor(((Shirt) entity).getColor());
        }
    }
}
