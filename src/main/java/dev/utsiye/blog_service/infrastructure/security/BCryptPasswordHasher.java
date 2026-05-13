package dev.utsiye.blog_service.infrastructure.security;

import dev.utsiye.blog_service.application.services.PasswordHasher;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;


@Component
public class BCryptPasswordHasher implements PasswordHasher{
    @Override
    public String hash(String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    @Override
    public boolean verify(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}