package local.system.calculatorrest.filter;

import local.system.common.core.enumerator.PropertiesEnum;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class MDCFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        String requestId = UUID.randomUUID().toString();
        MDC.put(PropertiesEnum.REQUEST_ID.getMessage(), requestId);

        HttpServletResponse httpResponse = (HttpServletResponse) httpServletResponse;
        httpResponse.addHeader(PropertiesEnum.REQUEST_ID.getMessage(), requestId);

        filterChain.doFilter(httpServletRequest, httpServletResponse);

        MDC.remove(PropertiesEnum.REQUEST_ID.getMessage());
    }
}
