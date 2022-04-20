package local.system.calculatorrest.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public abstract class RabbitConfig {

    @Value("${operator.sum-operator}")
    protected String sumOperatorProp;

    @Value("${operator.subtract-operator}")
    protected String subtractOperatorProp;

    @Value("${operator.multiply-operator}")
    protected String multiplyOperatorProp;

    @Value("${operator.divide-operator}")
    protected String divideOperatorProp;
}
