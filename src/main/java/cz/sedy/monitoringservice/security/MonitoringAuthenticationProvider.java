package cz.sedy.monitoringservice.security;

import cz.sedy.monitoringservice.domain.User;
import cz.sedy.monitoringservice.exception.UnknownUserException;
import cz.sedy.monitoringservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MonitoringAuthenticationProvider implements AuthenticationProvider {

    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MonitoringAuthentication monitoringAuthentication = (MonitoringAuthentication) authentication;
        User user = userRepository.findByAccessToken(monitoringAuthentication.getAccessToken())
                .orElseThrow(UnknownUserException::new);
        monitoringAuthentication.setUser(user);
        return monitoringAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MonitoringAuthentication.class.isAssignableFrom(authentication);
    }
}
