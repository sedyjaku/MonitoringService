package cz.sedy.monitoringservice.mapping;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class StringMapper {

    public String mapFromUUID(UUID uuid){
        if(uuid == null){
            return null;
        }
        return uuid.toString();
    }
}
