package dev.utsiye.blog_service.infrastructure.persistence.repositories;

import dev.utsiye.blog_service.infrastructure.persistence.entities.BookEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByAuthorAndIsDeletedFalse(String author);
    List<BookEntity> findByCategoryIdAndIsDeletedFalse(Long categoryId);

    @Query("SELECT b FROM BookEntity b WHERE b.isDeleted = false ORDER BY b.id DESC")
    List<BookEntity> findAllNotDeleted();
}
