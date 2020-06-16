package com.kafka.producer.kakfaproducer;

import com.albertsons.ecommerce.oms.model.core.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Rajni Kanth Tupakula
 */
@Component
public class Producer {

  @Autowired private KafkaTemplate kafkaTemplate;

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private static int COUNT = 1;

  @Scheduled(fixedRate = 200)
  public void produceMessage() {

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("orderFeed/1246_Order.json").getFile());
    try {
      Order order = OBJECT_MAPPER.readValue(file, Order.class);
      order.setOrderNumber(order.getOrderNumber() + COUNT++);
      kafkaTemplate.send("EMOM_ORDER_TOPIC_DEV", OBJECT_MAPPER.readValue(file, Order.class));
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
