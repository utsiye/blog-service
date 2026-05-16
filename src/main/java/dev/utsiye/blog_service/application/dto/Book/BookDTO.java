package dev.utsiye.blog_service.application.dto.Book;

public record BookDTO(
    Long id,
    String title,
    String description,
    String author,
    Long categoryId
) {}
