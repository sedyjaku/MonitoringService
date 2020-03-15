package cz.sedy.monitoringservice.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super();
    }

    public NotFoundException(String message){
        super(message);
    }

    public static NotFoundException by(Class<?> domainClass, String id){
        return new NotFoundException(String.format("Entity %s with id %s not found", domainClass.getSimpleName(), id));
    }

    public static NotFoundException by(Class<?> domainClass, UUID id){
        return new NotFoundException(String.format("Entity %s with id %s not found", domainClass.getSimpleName(), id));
    }
}
