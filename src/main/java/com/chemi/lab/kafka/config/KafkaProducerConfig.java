package com.chemi.lab.kafka.config;

import com.chemi.lab.contact_us.ContactUs;
import com.chemi.lab.kafka.data.outbound.EmailVerify;
import com.chemi.lab.kafka.data.outbound.SoilResultSMSNotification;
import com.chemi.lab.kafka.serializer.ContactSerializer;
import com.chemi.lab.kafka.serializer.EmailVerifySerializer;
import com.chemi.lab.kafka.serializer.SMSNotificationSerializer;
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
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String ,String > producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public ProducerFactory<String , ContactUs> producerFactoryContact(){
        Map<String, Object> configs = producerConfig();
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ContactSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public ProducerFactory<String , SoilResultSMSNotification> producerFactorySMSNotification(){
        Map<String, Object> configs = producerConfig();
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SMSNotificationSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }
    @Bean
    public ProducerFactory<String , EmailVerify> producerFactoryVerifyEmail(){
        Map<String, Object> configs = producerConfig();
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EmailVerifySerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(
            ProducerFactory<String ,String > producerFactory
    ){
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTemplate<String,ContactUs> kafkaTemplateContact(
            ProducerFactory<String ,ContactUs > producerFactory
    ){
        return new KafkaTemplate<>(producerFactory);
    }
    @Bean
    public KafkaTemplate<String, SoilResultSMSNotification> kafkaTemplateSMSNotification(
            ProducerFactory<String , SoilResultSMSNotification> producerFactory
    ){
        return new KafkaTemplate<>(producerFactory);
    }
    @Bean
    public KafkaTemplate<String,EmailVerify> kafkaTemplateEmailVerify(
            ProducerFactory<String ,EmailVerify > producerFactory
    ){
        return new KafkaTemplate<>(producerFactory);
    }


}
