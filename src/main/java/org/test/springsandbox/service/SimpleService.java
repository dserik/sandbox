
package org.test.springsandbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.test.springsandbox.config.SampleProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleService {
    private final SampleProperties properties;

    @Value("${sample-property.name}")
    private String name;

    public void changeTest() {
        log.info("service: after changed: field value = {}, property.value = {}",
                name,
                properties.getName());
    }

}
