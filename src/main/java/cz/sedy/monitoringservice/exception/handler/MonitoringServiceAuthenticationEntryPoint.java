package cz.sedy.monitoringservice.exception.handler;

import cz.sedy.monitoringservice.exception.UnknownUserException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MonitoringServiceAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
    }

    @ExceptionHandler(value = {UnknownUserException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         UnknownUserException unknownUserException) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + unknownUserException.getMessage());
    }

}