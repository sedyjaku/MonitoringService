package cz.sedy.monitoringservice.mapping;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UUIDMapper {

    public UUID mapFromString(String uuid){
        if(uuid == null){
            return null;
        }
        return UUID.fromString(uuid);
    }
}
