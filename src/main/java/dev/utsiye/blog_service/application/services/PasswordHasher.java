package dev.utsiye.blog_service.application.services;

public interface PasswordHasher {
    String hash(String plainPassword);
    boolean verify(String plainPassword, String hashedPassword);
}
