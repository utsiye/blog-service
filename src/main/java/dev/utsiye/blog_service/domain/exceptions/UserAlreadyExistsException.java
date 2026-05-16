package dev.utsiye.blog_service.domain.exceptions;

public class UserAlreadyExistsException extends BaseException{
    private final static String message = "User already exists";
    public UserAlreadyExistsException(){
        super(message);
    }
}