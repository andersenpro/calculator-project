package local.system.calculator.interceptor;

import local.system.common.core.enumerator.PropertiesEnum;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MdcInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object[] args = methodInvocation.getArguments();
        Message message = (Message) args[1];
        String requestId = (String) message
                .getMessageProperties()
                .getHeaders()
                .get(PropertiesEnum.REQUEST_ID.getMessage());

        if (requestId == null || requestId.isEmpty()) {
            requestId = UUID.randomUUID().toString();
            message.getMessageProperties().setHeader(PropertiesEnum.REQUEST_ID.getMessage(), requestId);
        }

        MDC.put(PropertiesEnum.REQUEST_ID.getMessage(), requestId);

        try {
            return methodInvocation.proceed();
        } finally {
            MDC.remove(PropertiesEnum.REQUEST_ID.getMessage());
        }
    }
}
