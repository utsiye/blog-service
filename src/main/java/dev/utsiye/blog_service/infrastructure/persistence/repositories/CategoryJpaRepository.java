package dev.utsiye.blog_service.infrastructure.persistence.repositories;

import dev.utsiye.blog_service.infrastructure.persistence.entities.CategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}
