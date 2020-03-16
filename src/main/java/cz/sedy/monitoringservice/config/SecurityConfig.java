package cz.sedy.monitoringservice.config;

import cz.sedy.monitoringservice.controller.filter.AuthenticationFilter;
import cz.sedy.monitoringservice.exception.handler.MonitoringServiceAuthenticationEntryPoint;
import cz.sedy.monitoringservice.security.MonitoringAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MonitoringAuthenticationProvider monitoringAuthenticationProvider;

    protected SecurityConfig() {
        super(true);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(monitoringAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.exceptionHandling().authenticationEntryPoint(new MonitoringServiceAuthenticationEntryPoint());
        httpSecurity.addFilterAt(new AuthenticationFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests().anyRequest().permitAll();
    }


}
