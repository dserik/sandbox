package org.test.springsandbox.service;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MqService {

  @RabbitListener(queues = "receiver.queue", returnExceptions = "true")
  private String getTime(String name) {
    log.error("name: {}", name);
    if (name.equals("exception")) {
      throw new ArithmeticException("fark u");
    }
    return Instant.now().toString();
  }
}
