package dev.utsiye.blog_service.application.dto.Book;

public record BookCreationRequestDTO(
    String title,
    String description,
    String author,
    Long categoryId
) {}
