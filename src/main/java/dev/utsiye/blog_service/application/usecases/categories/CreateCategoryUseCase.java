package dev.utsiye.blog_service.application.usecases.categories;

import dev.utsiye.blog_service.application.dto.Category.CategoryCreationRequestDTO;
import dev.utsiye.blog_service.application.dto.Category.CategoryDTO;
import dev.utsiye.blog_service.domain.entities.Category;
import dev.utsiye.blog_service.domain.exceptions.CategoryAlreadyExistsException;
import dev.utsiye.blog_service.domain.repositories.CategoryRepository;

import org.springframework.stereotype.Service;


@Service
public class CreateCategoryUseCase {
    private final CategoryRepository categoryRepo;

    public CreateCategoryUseCase(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public CategoryDTO execute(CategoryCreationRequestDTO categoryData) {
        if (categoryRepo.findByName(categoryData.name()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }

        Category category = categoryRepo.save(new Category(categoryData.name()));
        return new CategoryDTO(category.getId(), category.getName());
    }
}
