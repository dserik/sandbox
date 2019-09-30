package org.test.springsandbox.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.springsandbox.component.SimpleComponent;

@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor
public class SimpleRest {

    private final ApplicationContext applicationContext;


    // для проверки времени, расхода памяти при зоздании прототипа
    @GetMapping
    @RequestMapping("/{name}")
    public ResponseEntity<String> get(@PathVariable String name) {
        SimpleComponent bean = applicationContext.getBean(SimpleComponent.class, name);
        return ResponseEntity.ok(bean.getValue());
    }

}
