package cz.sedy.monitoringservice.exception;

import org.springframework.security.core.AuthenticationException;

public class UnknownUserException extends AuthenticationException {

    public UnknownUserException(String msg) {
        super(msg);
    }
}
