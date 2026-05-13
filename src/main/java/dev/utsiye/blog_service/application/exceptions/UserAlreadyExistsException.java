package dev.utsiye.blog_service.application.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String name){
        super("User with name " + name + " already exists");
    }
}
