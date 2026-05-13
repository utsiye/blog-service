package dev.utsiye.blog_service.presentation.controllers;

import dev.utsiye.blog_service.application.dto.UserResponseDTO;
import dev.utsiye.blog_service.application.dto.UserRequestDTO;
import dev.utsiye.blog_service.application.usecases.RegisterUserUseCase;
import dev.utsiye.blog_service.application.usecases.LoginUserUseCase;
import dev.utsiye.blog_service.application.dto.LoginRequestDTO;
import dev.utsiye.blog_service.application.dto.LoginResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    
    public UserController(RegisterUserUseCase registerUserUseCase, LoginUserUseCase loginUserUseCase){
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
