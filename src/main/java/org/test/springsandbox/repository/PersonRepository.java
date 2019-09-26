package org.test.springsandbox.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.test.springsandbox.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Page<Person> findAllByFullNameLike(String like, Pageable pageable);
}
