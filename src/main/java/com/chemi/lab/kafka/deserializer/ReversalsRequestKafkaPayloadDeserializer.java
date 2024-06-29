package com.chemi.lab.kafka.deserializer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpesa_c2b.mpesa_c2b.reversals.models.ReversalsRequestKafkaPayload;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class ReversalsRequestKafkaPayloadDeserializer implements Deserializer<ReversalsRequestKafkaPayload> {

    private final ObjectMapper objectMapper;

    public ReversalsRequestKafkaPayloadDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No additional configuration needed
    }

    @Override
    public ReversalsRequestKafkaPayload deserialize(String topic, byte[] data) {
        try {
            if (data == null)
                return null;

            return objectMapper.readValue(data, ReversalsRequestKafkaPayload.class);
        } catch (IOException e) {
            // Handle any deserialization errors here
            throw new RuntimeException("Error deserializing Kafka message", e);
        }
    }

    @Override
    public void close() {
        // No resource cleanup needed
    }
}
