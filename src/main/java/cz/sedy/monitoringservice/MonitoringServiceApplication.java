package cz.sedy.monitoringservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MonitoringServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoringServiceApplication.class, args);
    }

}
