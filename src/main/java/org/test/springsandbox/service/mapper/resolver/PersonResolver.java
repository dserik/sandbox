package org.test.springsandbox.service.mapper.resolver;

import lombok.RequiredArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;
import org.test.springsandbox.domain.Person;
import org.test.springsandbox.repository.PersonRepository;
import org.test.springsandbox.web.dto.PersonDTO;

@Component
@RequiredArgsConstructor
public class PersonResolver {

    private final PersonRepository repository;

    @SuppressWarnings("unused")
    @ObjectFactory
    public Person resolve(PersonDTO dto, @TargetType Class<Person> type) {
        if (dto != null) {
            if (dto.getId() != null) {
                return this.repository.getOne(dto.getId());
            }
        }
        return new Person();
    }

}