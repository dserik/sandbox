package org.test.springsandbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.springsandbox.domain.Person;
import org.test.springsandbox.exception.RecordNotFoundException;
import org.test.springsandbox.repository.PersonRepository;
import org.test.springsandbox.service.mapper.PersonMapper;
import org.test.springsandbox.web.dto.PersonDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonDTO getPerson(Long personId) {
        Person person = find(personId);
        return personMapper.toDto(person);
    }

    private Person find(Long personId) {
        return personRepository.findById(personId).orElseThrow(
                () -> new RecordNotFoundException("Person with id '" + personId + "' not found"));
    }

    public Page<PersonDTO> getAllPerson(Pageable pageable, String search) {
        String like = "%" + search + "%";

        Page<Person> filteredJournalList = personRepository.findAllByFullNameLike(like, pageable);
        List<PersonDTO> dtos = personMapper.toDto(filteredJournalList.getContent());

        return new PageImpl<>(dtos, pageable, filteredJournalList.getTotalElements());
    }

    @Transactional
    public void createPerson(PersonDTO dto) {
        dto.setId(null);
        Person person = personMapper.toEntity(dto);
        personRepository.save(person);
    }

    @Transactional
    public void deletePerson(Long personId) {
        Person person = find(personId);
        personRepository.delete(person);
    }

    @Transactional
    public void updatePerson(PersonDTO dto) {
        // validate existence
        find(dto.getId());

        Person person = personMapper.toEntity(dto);
        personRepository.save(person);
    }
}
