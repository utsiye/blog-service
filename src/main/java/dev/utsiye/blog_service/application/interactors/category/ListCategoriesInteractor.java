package dev.utsiye.blog_service.application.interactors.category;

import dev.utsiye.blog_service.application.dto.Category.CategoryDTO;
import dev.utsiye.blog_service.domain.repositories.CategoryRepository;
import dev.utsiye.blog_service.domain.entities.Category;

import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ListCategoriesInteractor {
    private final CategoryRepository categoryRepo;

    public ListCategoriesInteractor(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<CategoryDTO> execute() {
        List<Category> categories = categoryRepo.getAll();
        return categories.stream().map(cat -> new CategoryDTO(cat.getId(), cat.getName())).toList();
    }
}
