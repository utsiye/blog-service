package dev.utsiye.blog_service.domain.entities;

public class Book {
    private final Long id;
    private final String title;
    private final String description;
    private final String author;
    private final Long categoryId;
    private final boolean isDeleted;

    public Book(String title, String description, String author, Long categoryId) {
        validate(title, description, author);
        this.id = null;
        this.title = title;
        this.description = description;
        this.author = author;
        this.categoryId = categoryId;
        this.isDeleted = false;
    }

    public Book(Long id, String title, String description, String author, Long categoryId, boolean isDeleted) {
        validate(title, description, author);
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.categoryId = categoryId;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    private static void validate(String title, String description, String author) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }

        if (title.length() > 255) {
            throw new IllegalArgumentException("Book title cannot exceed 255 characters");
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Book description cannot be empty");
        }

        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Book author cannot be empty");
        }

        if (author.length() > 100) {
            throw new IllegalArgumentException("Book author cannot exceed 100 characters");
        }
    }

    @Override
    public String toString() {
        return "Book{"
                + "id=" + id + ","
                + "title=" + title + ","
                + "author=" + author + ","
                + "isDeleted=" + isDeleted
                + "}";
    }
}