package org.test.springsandbox.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date birthDate;
}
