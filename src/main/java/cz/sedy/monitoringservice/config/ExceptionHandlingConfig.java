package cz.sedy.monitoringservice.config;

import cz.sedy.monitoringservice.exception.handler.RestExceptionHandler;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@ImportAutoConfiguration({
        RestExceptionHandler.class
})
public class ExceptionHandlingConfig {
}
