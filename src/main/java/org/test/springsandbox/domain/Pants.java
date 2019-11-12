/*
 * Copyright (c) 2017 - 2018 ICORE Software Development LLP
 * http://icode.kz
 */
package org.test.springsandbox.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Pants extends Clothes {

    @Column
    private Integer size;
}
