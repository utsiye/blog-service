package dev.utsiye.blog_service.application.dto.Book;

public record BookUpdateRequestDTO(
    String title,
    String description,
    String author,
    Long categoryId
) {}
