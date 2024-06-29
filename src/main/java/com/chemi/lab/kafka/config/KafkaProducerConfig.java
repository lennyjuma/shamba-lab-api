package com.chemi.lab.kafka.config;

import com.mpesa_c2b.mpesa_c2b.CTOB.mpesa_acknowledgement.AcknowledgmentDto;
import com.mpesa_c2b.mpesa_c2b.CTOB.mpesa_result.MpesaResultDto;
import com.mpesa_c2b.mpesa_c2b.kafka.serializer.AcknowledgmentDtoSerializer;
import com.mpesa_c2b.mpesa_c2b.kafka.serializer.MpesaResultSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    public Map<String,Object> producerConfig(){
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String , AcknowledgmentDto> producerFactoryForAcknowledgement(){
        Map<String, Object> configs = producerConfig();
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AcknowledgmentDtoSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }
    @Bean
    public ProducerFactory<String , MpesaResultDto> producerFactoryForMpesaResults(){
        Map<String, Object> configs = producerConfig();
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, MpesaResultSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, AcknowledgmentDto> kafkaTemplateForAcknowledgement(
            ProducerFactory<String ,AcknowledgmentDto > producerFactory
    ){
        return new KafkaTemplate<>(producerFactory);
    }
    @Bean
    public KafkaTemplate<String, MpesaResultDto> kafkaTemplateForMpesaResults(
            ProducerFactory<String ,MpesaResultDto > producerFactory
    ){
        return new KafkaTemplate<>(producerFactory);
    }
}
