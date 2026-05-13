package dev.utsiye.blog_service.application.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){
        super("Invalid credentials");
    }
}
