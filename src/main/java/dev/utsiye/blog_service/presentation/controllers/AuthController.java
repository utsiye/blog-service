package dev.utsiye.blog_service.presentation.controllers;

import dev.utsiye.blog_service.application.dto.Login.LoginRequestDTO;
import dev.utsiye.blog_service.application.dto.Login.LoginResponseDTO;
import dev.utsiye.blog_service.application.dto.User.UserRequestDTO;
import dev.utsiye.blog_service.application.dto.User.UserResponseDTO;
import dev.utsiye.blog_service.application.interactors.auth.LoginUserInteractor;
import dev.utsiye.blog_service.application.interactors.auth.RegisterUserInteractor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class AuthController {
    private final RegisterUserInteractor registerUserUseCase;
    private final LoginUserInteractor loginUserUseCase;
    
    public AuthController(RegisterUserInteractor registerUserUseCase, LoginUserInteractor loginUserUseCase){
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO request){
        UserResponseDTO response = registerUserUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO request){
        LoginResponseDTO response = loginUserUseCase.execute(request);
        return ResponseEntity.ok(response);
    }
}
