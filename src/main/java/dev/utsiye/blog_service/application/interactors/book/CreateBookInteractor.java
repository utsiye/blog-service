package dev.utsiye.blog_service.application.interactors.book;

import dev.utsiye.blog_service.application.dto.Book.BookCreationRequestDTO;
import dev.utsiye.blog_service.application.dto.Book.BookDTO;
import dev.utsiye.blog_service.domain.entities.Book;
import dev.utsiye.blog_service.domain.repositories.BookRepository;

import org.springframework.stereotype.Service;


@Service
public class CreateBookInteractor {
    private final BookRepository bookRepo;

    public CreateBookInteractor(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public BookDTO execute(BookCreationRequestDTO bookData) {
        Book savedBook = bookRepo.save(new Book(
                bookData.title(),
                bookData.description(),
                bookData.author(),
                bookData.categoryId()
        ));

        return new BookDTO(
                savedBook.getId(),
                savedBook.getTitle(),
                savedBook.getDescription(),
                savedBook.getAuthor(),
                savedBook.getCategoryId()
        );
    }
}
