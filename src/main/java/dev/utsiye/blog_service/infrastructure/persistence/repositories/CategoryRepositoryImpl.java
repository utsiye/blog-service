package dev.utsiye.blog_service.infrastructure.persistence.repositories;

import dev.utsiye.blog_service.domain.repositories.CategoryRepository;
import dev.utsiye.blog_service.infrastructure.persistence.entities.CategoryEntity;
import dev.utsiye.blog_service.domain.entities.Category;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryRepositoryImpl implements CategoryRepository{
    private final CategoryJpaRepository jpaRepository;

    public CategoryRepositoryImpl(CategoryJpaRepository jpaRepository){
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Category save(Category category){
        CategoryEntity entity = toEntity(category);
        CategoryEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return jpaRepository.findById(categoryId)
                .map(this::toDomain);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(this::toDomain);
    }

    @Override
    public List<Category> getAll(){
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(Long categoryId) {
        jpaRepository.deleteById(categoryId);
    }

    private CategoryEntity toEntity(Category category) {
        CategoryEntity entity = new CategoryEntity(category.getName());
        if (category.getId() != null) {
            entity.setId(category.getId());
        }
        return entity;
    }

    private Category toDomain(CategoryEntity entity) {
        return new Category(entity.getId(), entity.getName());
    }
}
