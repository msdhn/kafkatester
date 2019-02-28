package com.eon.dapoc.kafkatester;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Configuration
@ConfigurationProperties("application")
public class KafkaTesterApplicationConfig {

    @Getter
    @Setter
    public AtomicBoolean enableProducing = new AtomicBoolean(false);

    @Getter
    @Setter
    private List<String> topics;
}
