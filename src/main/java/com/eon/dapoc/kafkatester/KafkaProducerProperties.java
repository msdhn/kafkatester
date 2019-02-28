package com.eon.dapoc.kafkatester;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
@ConfigurationProperties("kafka.producer")
public class KafkaProducerProperties {

    @Setter
    @Getter
    private List<String> topics;

    @Setter
    private Map<String, String> properties;

    public Properties getProducerProperties() {
        Properties properties = new Properties();
        this.properties.forEach((key, val) -> properties.put(key, val));
        return properties;
    }
}
