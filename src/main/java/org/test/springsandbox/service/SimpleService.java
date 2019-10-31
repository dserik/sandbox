
package org.test.springsandbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.test.springsandbox.config.SampleProperties;
import org.test.springsandbox.service.dto.SimpleEvent;
import org.test.springsandbox.service.dto.VoidEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleService {
    private final SampleProperties properties;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Value("${sample-property.name}")
    private String name;

    public void changeTest() {
        log.info("service: after changed: field value = {}, property.value = {}",
                name,
                properties.getName());
    }

    public void testEventListeners() {
        // publish event without listener
        applicationEventPublisher.publishEvent(new VoidEvent("empty message"));

        // publish event which has listener
        applicationEventPublisher.publishEvent(new SimpleEvent("hello message"));
    }

    @EventListener
    public void handleEvent(SimpleEvent event) {
        System.out.println(event.getMessage());
    }
}
