package dev.utsiye.blog_service.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import dev.utsiye.blog_service.application.usecases.categories.ListCategoriesUseCase;
import dev.utsiye.blog_service.application.dto.Category.CategoryDTO;
import dev.utsiye.blog_service.application.dto.Category.CategoryCreationRequestDTO;
import dev.utsiye.blog_service.application.usecases.categories.CreateCategoryUseCase;


@RestController
@RequestMapping("/categories")
public class CatalogController {
    private final ListCategoriesUseCase listCategoriesUseCase;
    private final CreateCategoryUseCase createCategoryUseCase;
    
    public CatalogController(ListCategoriesUseCase listCategoriesUseCase, CreateCategoryUseCase createCategoryUseCase){
        this.listCategoriesUseCase = listCategoriesUseCase;
        this.createCategoryUseCase = createCategoryUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryCreationRequestDTO request){
        CategoryDTO response = createCategoryUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> listCategories(){
        List<CategoryDTO> response = listCategoriesUseCase.execute();
        return ResponseEntity.ok(response);
    }
}
