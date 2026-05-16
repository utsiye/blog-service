package dev.utsiye.blog_service.application.interactors.book;

import dev.utsiye.blog_service.application.dto.Book.BookDTO;
import dev.utsiye.blog_service.application.dto.Book.BookUpdateRequestDTO;
import dev.utsiye.blog_service.domain.entities.Book;
import dev.utsiye.blog_service.domain.exceptions.BookNotFoundException;
import dev.utsiye.blog_service.domain.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateBookInteractor {
    private final BookRepository bookRepo;

    public UpdateBookInteractor(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public BookDTO execute(Long id, BookUpdateRequestDTO bookData) {
        Book existingBook = bookRepo.findById(id).orElseThrow(BookNotFoundException::new);
        Book updatedBook = new Book(
                existingBook.getId(),
                bookData.title(),
                bookData.description(),
                bookData.author(),
                bookData.categoryId(),
                existingBook.isDeleted()
        );

        return toDetailsDTO(bookRepo.save(updatedBook));
    }

    private BookDTO toDetailsDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                book.getAuthor(),
                book.getCategoryId()
        );
    }
}
