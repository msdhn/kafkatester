package com.eon.dapoc.kafkatester.controller;

import com.eon.dapoc.kafkatester.KafkaProducerProperties;
import com.eon.dapoc.kafkatester.KafkaTesterApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@RestController
public class KakfaMessageProducerController {
    @Autowired
    private KafkaTesterApplicationConfig kafkaTesterApplicationConfig;

    @Autowired
    private KafkaProducerProperties kafkaProducerProperties;

    @PostMapping(value = "startproducing", produces = MediaType.TEXT_HTML_VALUE)
    public String startProducing() {
        this.kafkaTesterApplicationConfig.setEnableProducing(new AtomicBoolean(true));
        return "started producing dummy messages in topics " + this.kafkaProducerProperties.getTopics()
                .stream()
                .collect(Collectors.joining(","));
    }

    @PostMapping(value = "stopproducing", produces = MediaType.TEXT_HTML_VALUE)
    public String stopProducing() {
        this.kafkaTesterApplicationConfig.setEnableProducing(new AtomicBoolean(false));
        return "stopped producing dummy messages in topics " + this.kafkaProducerProperties.getTopics()
                .stream()
                .collect(Collectors.joining(","));
    }
}
