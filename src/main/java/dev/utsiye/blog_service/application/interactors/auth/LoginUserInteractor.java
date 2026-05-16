package dev.utsiye.blog_service.application.interactors.auth;

import dev.utsiye.blog_service.application.dto.Login.LoginRequestDTO;
import dev.utsiye.blog_service.application.dto.Login.LoginResponseDTO;
import dev.utsiye.blog_service.domain.entities.User;
import dev.utsiye.blog_service.domain.exceptions.InvalidCredentialsException;
import dev.utsiye.blog_service.domain.exceptions.UserNotFoundException;
import dev.utsiye.blog_service.domain.repositories.UserRepository;
import dev.utsiye.blog_service.application.services.JWTTokenProvider;
import dev.utsiye.blog_service.application.services.PasswordHasher;

import org.springframework.stereotype.Service;

@Service
public class LoginUserInteractor {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final JWTTokenProvider jwtTokenProvider;

    public LoginUserInteractor(UserRepository userRepository, PasswordHasher passwordHasher, JWTTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponseDTO execute(LoginRequestDTO loginRequest) {
        User user = userRepository.findByName(loginRequest.name())
                .orElseThrow(() -> new UserNotFoundException());

        if (!passwordHasher.verify(loginRequest.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtTokenProvider.generateToken(user.getId());

        return new LoginResponseDTO(token, user.getId());
    }
}
