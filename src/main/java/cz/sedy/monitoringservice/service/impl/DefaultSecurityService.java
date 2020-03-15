package cz.sedy.monitoringservice.service.impl;

import cz.sedy.monitoringservice.domain.User;
import cz.sedy.monitoringservice.service.SecurityService;
import org.springframework.security.core.context.SecurityContextHolder;

public class DefaultSecurityService implements SecurityService {
    @Override
    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
