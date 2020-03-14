package cz.sedy.monitoringservice.domain;

import java.util.UUID;

public interface IdentifiedDomain extends Domain{

    public UUID getId();
}
