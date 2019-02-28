package com.eon.dapoc.kafkatester.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaTestProducerCallback implements Callback {

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        //TODO implement error handling and all
        if (exception != null) {
            exception.printStackTrace();
        }
    }
}
