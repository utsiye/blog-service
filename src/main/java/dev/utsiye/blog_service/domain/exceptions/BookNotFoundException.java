package dev.utsiye.blog_service.domain.exceptions;

public class BookNotFoundException extends BaseException {
    public BookNotFoundException() {
        super("Book not found");
    }
}
