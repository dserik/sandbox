/*
 * Copyright (c) 2017 - 2018 ICORE Software Development LLP
 * http://icode.kz
 */
package org.test.springsandbox.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
}

