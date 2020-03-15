package cz.sedy.monitoringservice.security;

import cz.sedy.monitoringservice.domain.User;
import javax.security.auth.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonitoringAuthentication extends AbstractAuthenticationToken{

    String accessToken;

    @Setter
    User user;

    public MonitoringAuthentication(String accessToken) {
        super(null);
        this.accessToken = accessToken;
    }

    @Override
    public String getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
