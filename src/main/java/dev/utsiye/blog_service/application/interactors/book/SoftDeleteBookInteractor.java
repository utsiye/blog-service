package dev.utsiye.blog_service.application.interactors.book;

import dev.utsiye.blog_service.domain.exceptions.BookNotFoundException;
import dev.utsiye.blog_service.domain.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class SoftDeleteBookInteractor {
    private final BookRepository bookRepo;

    public SoftDeleteBookInteractor(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void execute(Long id) {
        if (bookRepo.findById(id).isEmpty()) {
            throw new BookNotFoundException();
        }
        bookRepo.deleteById(id);
    }
}
