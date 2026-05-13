package dev.utsiye.blog_service.domain.repositories;

import dev.utsiye.blog_service.domain.entities.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
}