package pl.entpoint.harmony.config.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mateusz Dąbek
 * @created 29 maj 2020
 * 
 */


@Component
@Order(2)
@Slf4j
public class ReqRespLoggingFilter implements Filter {
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
  
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        log.info("Logging filter Request  {}: {}. IP: {} login:{}", req.getMethod(), req.getRequestURI(), req.getRemoteAddr(), req.getRemoteUser());
        
        chain.doFilter(request, response);
        
        log.info("Logging filter Response: {} status: {}", res.getContentType(), res.getStatus());
    }
}