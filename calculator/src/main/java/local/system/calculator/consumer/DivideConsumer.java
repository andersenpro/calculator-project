package local.system.calculator.consumer;

import local.system.common.core.dto.OperandsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Component
@RabbitListener(queues = "${queue.divide-queue}")
public class DivideConsumer {

    @RabbitHandler
    public BigDecimal receiveMessage(OperandsDTO operandsDTO) {
        log.info("received message " + operandsDTO);
        BigDecimal result = operandsDTO.getA().divide(operandsDTO.getB(), 10, RoundingMode.FLOOR);

        log.info("Division result: " + result);
        return result;
    }
}
