package org.test.springsandbox.test.mapstruct.entities;

import lombok.Getter;
import lombok.Setter;
import org.test.springsandbox.web.dto.PersonDTO;

import java.util.List;

@Getter @Setter
public class CompanyDTO {
    private Long id;
    private String companyName;
    private String code;
    private List<PersonDTO> personal;

    private SomeDTO sampleField;
}
