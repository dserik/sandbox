package org.test.springsandbox.test.mapstruct.entities;

import lombok.Getter;
import lombok.Setter;
import org.test.springsandbox.domain.Person;

import java.util.List;

@Getter @Setter
public class Company {
    private Long id;
    private String name;
    private String code;
    private List<Person> personList;
}
