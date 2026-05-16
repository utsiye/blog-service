package dev.utsiye.blog_service.application.dto.Book;

public record BookListFilterRequestDTO(
    Long categoryId,
    String author,
    int offset,
    int limit
) {}
