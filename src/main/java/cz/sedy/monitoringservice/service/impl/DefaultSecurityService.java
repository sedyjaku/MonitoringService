package cz.sedy.monitoringservice.service.impl;

import cz.sedy.monitoringservice.domain.User;
import cz.sedy.monitoringservice.exception.UserNotAuthorizedException;
import cz.sedy.monitoringservice.repository.UserRepository;
import cz.sedy.monitoringservice.service.SecurityService;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultSecurityService implements SecurityService {

    UserRepository userRepository;

    @Override
    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional
    public void checkUserAuthorityOnMonitoredEndpoint(UUID monitoredEndpointId) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getOne(authenticatedUser.getId());
        if (user.getMonitoredEndpoints().stream()
                .anyMatch(endpoint -> endpoint.getId().equals(monitoredEndpointId.toString()))
        ){
            return;
        }
        throw new UserNotAuthorizedException();
    }
}
