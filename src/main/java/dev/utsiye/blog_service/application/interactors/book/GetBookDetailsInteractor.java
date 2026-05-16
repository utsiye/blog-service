package dev.utsiye.blog_service.application.interactors.book;

import dev.utsiye.blog_service.application.dto.Book.BookDTO;
import dev.utsiye.blog_service.domain.exceptions.BookNotFoundException;
import dev.utsiye.blog_service.domain.repositories.BookRepository;

import org.springframework.stereotype.Service;


@Service
public class GetBookDetailsInteractor {
    private final BookRepository bookRepo;

    public GetBookDetailsInteractor(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public BookDTO execute(Long id) {
        return bookRepo.findById(id)
                .map(this::toDTO)
                .orElseThrow(BookNotFoundException::new);
    }

    private BookDTO toDTO(dev.utsiye.blog_service.domain.entities.Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                book.getAuthor(),
                book.getCategoryId()
        );
    }
}
