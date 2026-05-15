package dev.utsiye.blog_service.application.dto.Login;

public record LoginResponseDTO (
    String token,
    long userId
){}
