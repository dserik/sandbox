
package org.test.springsandbox.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "sample-property")
public class SampleProperties {

    private String name;
    private Long value;

}
