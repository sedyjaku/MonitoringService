package cz.sedy.monitoringservice.exception;

import cz.sedy.monitoringservice.domain.IdentifiedDomain;
import java.util.UUID;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super();
    }

    public NotFoundException(String message){
        super(message);
    }

    public static NotFoundException by(Class<?> domainClass, UUID id){
        return new NotFoundException(String.format("Entity %s with id %s not found", domainClass.getSimpleName(), id));
    }
}
