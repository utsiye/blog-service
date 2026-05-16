package dev.utsiye.blog_service.application.interactors.book;

import dev.utsiye.blog_service.application.dto.Book.BookListFilterRequestDTO;
import dev.utsiye.blog_service.application.dto.Book.BookSummaryDTO;
import dev.utsiye.blog_service.domain.entities.Book;
import dev.utsiye.blog_service.domain.repositories.BookRepository;

import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ListBooksInteractor {
    private final BookRepository bookRepo;

    public ListBooksInteractor(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<BookSummaryDTO> execute(BookListFilterRequestDTO filter) {
        int offset = filter.offset();
        int limit = filter.limit();
        List<Book> books;
        if (filter.categoryId() != null) {
            books = bookRepo.findByCategoryId(filter.categoryId(), offset, limit);
        } else if (filter.author() != null && !filter.author().isBlank()) {
            books = bookRepo.findByAuthor(filter.author(), offset, limit);
        } else {
            books = bookRepo.getAll(offset, limit);
        }

        return books.stream()
                .map(this::toDTO)
                .toList();
    }

    private BookSummaryDTO toDTO(Book book) {
        return new BookSummaryDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategoryId()
        );
    }
}
