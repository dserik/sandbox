/*
 * Copyright (c) 2017 - 2018 ICORE Software Development LLP
 * http://icode.kz
 */
package org.test.springsandbox.test.mapstruct.resolvers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;
import org.test.springsandbox.test.mapstruct.entities.AbstractClass;
import org.test.springsandbox.test.mapstruct.entities.SomeDTO;


@Slf4j
@Component
@RequiredArgsConstructor
public class DictionaryResolver {

    @ObjectFactory
    public <T extends AbstractClass> T resolve(SomeDTO dto, @TargetType Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @ObjectFactory
    public <T extends AbstractClass> T resolve(Long id, @TargetType Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}