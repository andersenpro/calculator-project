package local.system.calculator.config;

import lombok.Data;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;

@Data
public abstract class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    protected String hostProp;

    @Value("${spring.rabbitmq.port}")
    protected int portProp;

    @Value("${spring.rabbitmq.username}")
    protected String userNameProp;

    @Value("${spring.rabbitmq.password}")
    protected String passwordProp;

    @Value("${queue.sum-queue}")
    protected String sumQueueProp;

    @Value("${queue.subtract-queue}")
    protected String subtractQueueProp;

    @Value("${queue.multiply-queue}")
    protected String multiplyQueueProp;

    @Value("${queue.divide-queue}")
    protected String divideQueueProp;

    @Value("${operator.sum-operator}")
    protected String sumOperatorProp;

    @Value("${operator.subtract-operator}")
    protected String subtractOperatorProp;

    @Value("${operator.multiply-operator}")
    protected String multiplyOperatorProp;

    @Value("${operator.divide-operator}")
    protected String divideOperatorProp;

    protected ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(hostProp);
        connectionFactory.setPort(portProp);
        connectionFactory.setUsername(userNameProp);
        connectionFactory.setPassword(passwordProp);
        return connectionFactory;
    }
}
