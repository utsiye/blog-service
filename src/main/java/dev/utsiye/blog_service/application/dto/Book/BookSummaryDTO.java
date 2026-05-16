package dev.utsiye.blog_service.application.dto.Book;

public record BookSummaryDTO(
    Long id,
    String title,
    String author,
    Long categoryId
) {}
