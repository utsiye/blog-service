package dev.utsiye.blog_service.application.usecases.categories;

import dev.utsiye.blog_service.application.dto.Category.CategoryDTO;
import dev.utsiye.blog_service.domain.repositories.CategoryRepository;
import dev.utsiye.blog_service.domain.entities.Category;

import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ListCategoriesUseCase {
    private final CategoryRepository categoryRepo;

    public ListCategoriesUseCase(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<CategoryDTO> execute() {
        List<Category> categories = categoryRepo.getAll();
        return categories.stream().map(cat -> new CategoryDTO(cat.getId(), cat.getName())).toList();
    }
}
