package org.test.springsandbox.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.springsandbox.component.SimpleComponent;
import org.test.springsandbox.config.SampleProperties;
import org.test.springsandbox.service.SimpleService;

@Slf4j
@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor
public class SimpleRest {

    private final ApplicationContext applicationContext;
    private final SampleProperties properties;
    private final SimpleService simpleService;

    @Value("${sample-property.name}")
    private String name;


    // для проверки времени, расхода памяти при создании прототипа
    @GetMapping
    @RequestMapping("/{name}")
    public ResponseEntity<String> get(@PathVariable String name) {
        SimpleComponent bean = applicationContext.getBean(SimpleComponent.class, name);
        return ResponseEntity.ok(bean.getValue());
    }

    @GetMapping("/changeTest")
    public void change() {
        log.info("controller: before changed: field value = {}, property.value = {}",
                name,
                properties.getName());

        properties.setName("changed");
        simpleService.changeTest();
    }

}
