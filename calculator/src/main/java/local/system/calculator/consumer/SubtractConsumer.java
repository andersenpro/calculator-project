package local.system.calculator.consumer;

import local.system.common.core.dto.OperandsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RabbitListener(queues = "${queue.subtract-queue}")
public class SubtractConsumer {

    @RabbitHandler
    public BigDecimal receiveMessage(OperandsDTO operandsDTO) {
        log.info("received message " + operandsDTO);
        BigDecimal result = operandsDTO.getA().subtract(operandsDTO.getB());

        log.info("Subtraction result: " + result);
        return result;
    }
}
