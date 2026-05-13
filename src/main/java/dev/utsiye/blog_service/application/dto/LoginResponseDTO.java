package dev.utsiye.blog_service.application.dto;

public record LoginResponseDTO (
    String token,
    long userId
){}
