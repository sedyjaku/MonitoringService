package cz.sedy.monitoringservice.service;

import cz.sedy.monitoringservice.domain.User;
import java.util.UUID;

public interface SecurityService {

    User getUser();

    void checkUserAuthorityOnMonitoredEndpoint(UUID monitoredEndpointId);
}
