package com.eon.dapoc.kafkatester.kafka;

import com.eon.dapoc.kafkatester.KafkaProducerProperties;
import com.eon.dapoc.kafkatester.SampleMessage;
import lombok.val;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Component
public class KafkaTestProducer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaTestProducer.class);
    private final Schema schema;
    private Producer<String, GenericRecord> producer;
    private KafkaProducerProperties kafkaProducerProperties;

    @Autowired
    public KafkaTestProducer(KafkaProducerProperties kafkaProducerProperties) throws FileNotFoundException, IOException {
        InputStream in = this.getClass().getResourceAsStream("/sampleschema.json");
        this.schema = new Schema.Parser().parse(in);
        this.kafkaProducerProperties = kafkaProducerProperties;
        val properties = kafkaProducerProperties.getProducerProperties();
        this.producer = new KafkaProducer<>(properties);
    }

    public void produceRecord(SampleMessage message) {
        for (String topic : this.kafkaProducerProperties.getTopics()) {
            val rc = new GenericData.Record(this.schema);
            rc.put("timeStamp", message.getTimeStamp());
            rc.put("message", message.getMessage() + "  -> " + topic);
            LOG.info("producing to topic " + topic);
            ProducerRecord<String, GenericRecord> producerRecord = new ProducerRecord<>(topic, message.getTimeStamp(), rc);
            try {
                this.producer.send(producerRecord, new KafkaTestProducerCallback());
            } catch (Exception ex) {
                //TODO :
                ex.printStackTrace();
            }
        }
    }
}
