package dev.utsiye.blog_service.domain.exceptions;

public class CategoryAlreadyExistsException extends BaseException {
    public CategoryAlreadyExistsException() {
        super("Category already exists");
    }
}
