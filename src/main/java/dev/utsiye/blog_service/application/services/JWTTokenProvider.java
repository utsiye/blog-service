package dev.utsiye.blog_service.application.services;

public interface JWTTokenProvider {
    String generateToken(long userId);
    long extractUserId(String token);
    boolean isValid(String token); 
}
