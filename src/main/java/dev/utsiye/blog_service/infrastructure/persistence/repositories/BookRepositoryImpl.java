package dev.utsiye.blog_service.infrastructure.persistence.repositories;

import dev.utsiye.blog_service.domain.repositories.BookRepository;
import dev.utsiye.blog_service.infrastructure.persistence.entities.BookEntity;
import dev.utsiye.blog_service.domain.entities.Book;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final BookJpaRepository jpaRepository;

    public BookRepositoryImpl(BookJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = toEntity(book);
        BookEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isDeleted())
                .map(this::toDomain);
    }

    @Override
    public List<Book> findByAuthor(String author, int offset, int limit) {
        return jpaRepository.findByAuthorAndIsDeletedFalse(author)
                .stream()
                .skip(offset)
                .limit(limit)
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Book> findByCategoryId(Long categoryId, int offset, int limit) {
        return jpaRepository.findByCategoryIdAndIsDeletedFalse(categoryId)
                .stream()
                .skip(offset)
                .limit(limit)
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.findById(id).ifPresent(entity -> {
            entity.setDeleted(true);
            jpaRepository.save(entity);
        });
    }

    @Override
    public List<Book> getAll(int offset, int limit) {
        return jpaRepository.findAllNotDeleted()
                .stream()
                .skip(offset)
                .limit(limit)
                .map(this::toDomain)
                .toList();
    }

    private BookEntity toEntity(Book book) {
        BookEntity entity = new BookEntity(book.getTitle(), book.getDescription(), book.getAuthor(), book.getCategoryId());
        if (book.getId() != null) {
            entity.setId(book.getId());
        }
        entity.setDeleted(book.isDeleted());
        return entity;
    }

    private Book toDomain(BookEntity entity) {
        return new Book(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getAuthor(), entity.getCategoryId(), entity.isDeleted());
    }
}
