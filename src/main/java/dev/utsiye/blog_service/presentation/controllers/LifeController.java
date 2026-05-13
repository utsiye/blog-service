package dev.utsiye.blog_service.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class LifeController {

    @GetMapping("/alive")
    public ResponseEntity<?> alive() {
        return ResponseEntity.ok(
            java.util.Map.of("ok", true)
        );
    }
}