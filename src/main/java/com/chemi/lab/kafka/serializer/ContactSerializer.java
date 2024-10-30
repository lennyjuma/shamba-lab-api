package com.chemi.lab.kafka.serializer;

import com.chemi.lab.contact_us.ContactUs;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class ContactSerializer implements Serializer<ContactUs> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No additional configuration needed
    }

    @Override
    public byte[] serialize(String topic, ContactUs data) {
        try {
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
