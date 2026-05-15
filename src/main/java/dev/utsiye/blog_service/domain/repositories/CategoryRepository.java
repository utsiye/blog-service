package dev.utsiye.blog_service.domain.repositories;

import dev.utsiye.blog_service.domain.entities.Category;
import java.util.Optional;
import java.util.List;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(Long categoryId);
    Optional<Category> findByName(String name);
    List<Category> getAll();
    void deleteById(Long categoryId);
}