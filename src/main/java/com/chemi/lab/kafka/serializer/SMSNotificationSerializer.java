package com.chemi.lab.kafka.serializer;

import com.chemi.lab.kafka.data.outbound.SoilResultSMSNotification;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class SMSNotificationSerializer implements Serializer<SoilResultSMSNotification> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No additional configuration needed
    }

    @Override
    public byte[] serialize(String topic, SoilResultSMSNotification data) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            if (data == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing AcknowledgmentDto", e);
        }
    }

    @Override
    public void close() {
        // No resources to be released
    }
}
