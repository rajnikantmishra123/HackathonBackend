package com.example.HackathonBackend.controller;

import com.example.HackathonBackend.dto.SignupRequestDto;
import com.example.HackathonBackend.dto.SignupResponseDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {



//    @PostMapping("/signup")
//    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
//        SignupResponseDto response = authService.signup(signupRequestDto);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
}
