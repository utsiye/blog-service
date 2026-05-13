package dev.utsiye.blog_service.domain.exceptions;

public class UserNotFoundException extends BaseException{
    private final static String message = "User is not found";
    public UserNotFoundException(){
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(message, cause);
    }
}