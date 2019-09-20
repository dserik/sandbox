package org.test.springsandbox.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.springsandbox.web.dto.Request;
import org.test.springsandbox.component.SimpleComponent;

import java.util.HashMap;
import java.util.Map;

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
