package org.test.springsandbox.web.rest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.test.springsandbox.service.PersonService;
import org.test.springsandbox.web.dto.PersonDTO;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Number of page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.")
    })
    @GetMapping
    public Page<PersonDTO> getAllPerson(
            @ApiParam Pageable pageable,
            @RequestParam(defaultValue = "", required = false) String search) {

        return personService.getAllPerson(pageable, search);
    }

    @GetMapping("/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO getPerson(@PathVariable Long personId) {
        return personService.getPerson(personId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createPerson(@RequestBody PersonDTO dto) {
        personService.createPerson(dto);
    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
    }

    @PutMapping("/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePerson(@PathVariable Long personId, @RequestBody PersonDTO dto) {
        dto.setId(personId);
        personService.updatePerson(dto);
    }
}
