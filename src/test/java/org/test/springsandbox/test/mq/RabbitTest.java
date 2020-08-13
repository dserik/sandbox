package org.test.springsandbox.test.mq;

import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.remoting.support.RemoteInvocationResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.springsandbox.SpringSandboxApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SpringSandboxApplication.class)
public class RabbitTest {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Test
  public void sendReceiveTest() {
    Object responseToMike = rabbitTemplate.convertSendAndReceive("receiver.exchange", "receiver.key", "mike");
    System.out.println(responseToMike);
    Assert.isTrue(responseToMike instanceof String);

    Object responseToEx = rabbitTemplate.convertSendAndReceive("receiver.exchange", "receiver.key", "exception");
    Assert.isTrue(responseToEx instanceof RemoteInvocationResult);
    Throwable exception = ((RemoteInvocationResult) responseToEx).getException();
    Assert.notNull(exception);
    System.out.println(exception.getMessage());
  }
}
