package dev.utsiye.blog_service.infrastructure.persistence.repositories;

import dev.utsiye.blog_service.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
}
