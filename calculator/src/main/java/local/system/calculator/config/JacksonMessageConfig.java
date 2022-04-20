package local.system.calculator.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

public class JacksonMessageConfig extends Jackson2JsonMessageConverter {
    public JacksonMessageConfig() {
        super();
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        message.getMessageProperties().setContentType("application/json");
        return super.fromMessage(message);
    }
}
