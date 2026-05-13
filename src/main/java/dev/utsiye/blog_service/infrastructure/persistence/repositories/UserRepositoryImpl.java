package dev.utsiye.blog_service.infrastructure.persistence.repositories;

import dev.utsiye.blog_service.domain.entities.User;
import dev.utsiye.blog_service.domain.repositories.UserRepository;
import dev.utsiye.blog_service.infrastructure.persistence.entities.UserEntity;

import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public class UserRepositoryImpl implements UserRepository{
    private final UserJpaRepository jpaRepository;

    public UserRepositoryImpl(UserJpaRepository jpaRepository){
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user){
        UserEntity entity = toEntity(user);
        UserEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(this::toDomain);
    }

    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity(user.getName(), user.getRole(), user.getPasswordHash());
        if (user.getId() != null) {
            entity.setId(user.getId());
        }
        return entity;
    }

    private User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getRole(), entity.getPasswordHash());
    }
}
