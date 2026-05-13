package dev.utsiye.blog_service.domain.exceptions;


public class InvalidCredentialsException extends BaseException{
    private final static String message = "Invalid credentials";
    public InvalidCredentialsException(){
        super(message);
    }

    public InvalidCredentialsException(Throwable cause) {
        super(message, cause);
    }
}
