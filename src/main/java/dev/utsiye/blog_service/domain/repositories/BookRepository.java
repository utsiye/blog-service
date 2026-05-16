package dev.utsiye.blog_service.domain.repositories;

import dev.utsiye.blog_service.domain.entities.Book;

import java.util.Optional;
import java.util.List;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findByAuthor(String author, int offset, int limit);
    List<Book> findByCategoryId(Long categoryId, int offset, int limit);
    void deleteById(Long id);
    List<Book> getAll(int offset, int limit);
}
