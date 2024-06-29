package com.chemi.lab.kafka.Listeners;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpesa_c2b.mpesa_c2b.CTOB.mpesa_checkout.C2BExpress;
import com.mpesa_c2b.mpesa_c2b.CTOB.mpesa_checkout.CheckoutService;
import com.mpesa_c2b.mpesa_c2b.express_query.services.ExpressQueryCheckout;
import com.mpesa_c2b.mpesa_c2b.kafka.config.models.HardwareFromMQTTDto;
import com.mpesa_c2b.mpesa_c2b.reversals.models.ReversalsRequestKafkaPayload;
import com.mpesa_c2b.mpesa_c2b.reversals.service.ReversalCheckout;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaListeners {
    private final CheckoutService checkoutService;
    private final ReversalCheckout reversalCheckout;
    private final ExpressQueryCheckout expressQueryCheckout;

    public KafkaListeners(CheckoutService checkoutService, ReversalCheckout reversalCheckout, ExpressQueryCheckout expressQueryCheckout) {
        this.checkoutService = checkoutService;
        this.reversalCheckout = reversalCheckout;
        this.expressQueryCheckout = expressQueryCheckout;
    }

    @KafkaListener(
            topics = "start_stk_from_mqtt",
            groupId = "groupId"
    )
    void listener(String data) throws IOException {
        System.out.printf("Listener received %S%n",data);
        HardwareFromMQTTDto mqttDto = new ObjectMapper().readValue(data, HardwareFromMQTTDto.class);
        C2BExpress express = new C2BExpress();
        express.setAmount(1);
        express.setPhoneNumber(mqttDto.getPhoneNumber());
        express.setValveUUID("254706754174");
        checkoutService.initiateExpressCheckout(mqttDto);
    }

    @KafkaListener(
            topics = "reversal_payload_chemichemi",
            groupId = "groupId"
    )
    void reversalListener(String data) throws Exception {
        System.out.printf("Listener received ReversalsRequestKafkaPayload %S%n",data);
        ReversalsRequestKafkaPayload kafkaPayload = new ObjectMapper().readValue(data, ReversalsRequestKafkaPayload.class);
        reversalCheckout.initiateExpressCheckout(kafkaPayload);
    }
    @KafkaListener(
            topics = "query_payload_chemichemi",
            groupId = "groupId"
    )
    void startQueryListener(String data) throws Exception {
        System.out.printf("Listener received Query payload %S%n",data);
        expressQueryCheckout.initiateExpressCheckout(data);
    }
}
