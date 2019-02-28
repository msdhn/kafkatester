package com.eon.dapoc.kafkatester.schedule;

import com.eon.dapoc.kafkatester.KafkaTesterApplicationConfig;
import com.eon.dapoc.kafkatester.SampleMessage;
import com.eon.dapoc.kafkatester.kafka.KafkaTestProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledProduceTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduledProduceTask.class);

    private KafkaTesterApplicationConfig kafkaTesterApplicationConfig;
    private KafkaTestProducer kafkaTestProducer;

    @Autowired
    public ScheduledProduceTask(KafkaTesterApplicationConfig kafkaTesterApplicationConfig,
                                KafkaTestProducer kafkaTestProducer) {
        this.kafkaTesterApplicationConfig = kafkaTesterApplicationConfig;
        this.kafkaTestProducer = kafkaTestProducer;
    }

    @Scheduled(fixedRate = 5 * 1000)
    public void produceMessage() {
        try {
            if (this.kafkaTesterApplicationConfig.getEnableProducing().get()) {
                SampleMessage sampleMessage = new SampleMessage();
                sampleMessage.setTimeStamp(String.valueOf(System.currentTimeMillis()));
                sampleMessage.setMessage("Dummy message produced at " + new Date());
                this.kafkaTestProducer.produceRecord(sampleMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}