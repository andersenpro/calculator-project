package local.system.calculator.config;

import local.system.calculator.interceptor.MdcInterceptor;
import local.system.common.core.enumerator.PropertiesEnum;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitServerConfig extends RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return super.connectionFactory();
    }

    @Bean(name = "rabbitListenerContainerFactory")
    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory());
        factory.setAdviceChain(new MdcInterceptor());
        return factory;
    }

    @Bean
    public AmqpAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.declareExchange(directExchange());
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(new JacksonMessageConfig());
        return rabbitTemplate;
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(PropertiesEnum.CALC_DIRECT_EXCHANGE.getMessage());
    }

    @Bean
    Queue sumQueue() {
        return new Queue(sumQueueProp, false);
    }

    @Bean
    Queue subtractQueue() {
        return new Queue(subtractQueueProp, false);
    }

    @Bean
    Queue multiplyQueue() {
        return new Queue(multiplyQueueProp, false);
    }

    @Bean
    Queue divideQueue() {
        return new Queue(divideQueueProp, false);
    }

    @Bean
    Binding sumBinding(Queue sumQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(sumQueue).to(directExchange).with(sumOperatorProp);
    }

    @Bean
    Binding subtractBinding(Queue subtractQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(subtractQueue).to(directExchange).with(subtractOperatorProp);
    }

    @Bean
    Binding multiplyBinding(Queue multiplyQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(multiplyQueue).to(directExchange).with(multiplyOperatorProp);
    }

    @Bean
    Binding divideBinding(Queue divideQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(divideQueue).to(directExchange).with(divideOperatorProp);
    }
}
