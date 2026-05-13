package dev.utsiye.blog_service.application.usecases;

import dev.utsiye.blog_service.domain.entities.User;
import dev.utsiye.blog_service.domain.repositories.UserRepository;
import dev.utsiye.blog_service.domain.enums.UserRole;
import dev.utsiye.blog_service.domain.exceptions.UserAlreadyExistsException;
import dev.utsiye.blog_service.application.services.PasswordHasher;
import dev.utsiye.blog_service.application.dto.UserResponseDTO;
import dev.utsiye.blog_service.application.dto.UserRequestDTO;

import org.springframework.stereotype.Service;


@Service
public class RegisterUserUseCase {
    private final UserRepository userRepo;
    private final PasswordHasher passwordHasher;

    public RegisterUserUseCase(UserRepository userRepo, PasswordHasher passwordHasher){
        this.userRepo = userRepo;
        this.passwordHasher = passwordHasher;
    }

    public UserResponseDTO execute(UserRequestDTO userData){
        if (userRepo.findByName(userData.name()) != null){
            throw new UserAlreadyExistsException();
        }

        String hashedPassword = passwordHasher.hash(userData.password());
        User user = userRepo.save(new User(userData.name(), UserRole.USER, hashedPassword));

        return new UserResponseDTO(user.getId(), user.getName());
    }
}
