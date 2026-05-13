package dev.utsiye.blog_service.application.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User was not found.");
    }
}
