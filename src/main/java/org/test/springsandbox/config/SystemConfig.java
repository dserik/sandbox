package org.test.springsandbox.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.test.springsandbox.component.SimpleComponent;

@Configuration
@EnableConfigurationProperties(SampleProperties.class)
public class SystemConfig {


    /**
     * создается явным вызовом applicationContext.getBean(SimpleService.class, value)
     *
     * @param value значение при инициализации
     * @return prototype bean
     */
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public SimpleComponent getService(String value) {
        System.out.println(value);
        return new SimpleComponent(value);
    }

}
