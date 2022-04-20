package local.system.calculatorrest.service;

import local.system.common.core.dto.OperandsDTO;
import local.system.common.core.enumerator.PropertiesEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

    private static final String REQUEST_ID = PropertiesEnum.REQUEST_ID.getMessage();
    private static final String CALC_DIRECT_EXCHANGE = PropertiesEnum.CALC_DIRECT_EXCHANGE.getMessage();
    private final RabbitTemplate rabbitTemplate;

    public void setRequestId() {
        rabbitTemplate.setBeforePublishPostProcessors(message -> {
            String requestId = MDC.get(REQUEST_ID);
            if (requestId != null && !requestId.isEmpty()) {
                message.getMessageProperties().setHeader(REQUEST_ID, requestId);
            }
            return message;
        });
    }

    public BigDecimal executeCalculator(BigDecimal a, BigDecimal b, String operation)
            throws NumberFormatException {
        log.info("received payload /" + operation + "?a=" + a + "&b=" + b);

        try {
            OperandsDTO operandsDTO = new OperandsDTO(a, b);
            setRequestId();

            BigDecimal result = (BigDecimal) rabbitTemplate
                    .convertSendAndReceive(CALC_DIRECT_EXCHANGE,
                    operation, operandsDTO);

            log.info("Received result " + result);
            return result;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid operand passed");
        }
    }

}
