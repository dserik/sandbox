package org.test.springsandbox.component;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// прототип создается из конфига
public class SimpleComponent {

    @Getter
    private String value;

    @Getter
    private List<String> list;

    // пример нагрузки памяти, времени при создании бина
    public SimpleComponent(String value) {
        this.value = value;
        this.list = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            list.add("bobby");
        }
    }
}
