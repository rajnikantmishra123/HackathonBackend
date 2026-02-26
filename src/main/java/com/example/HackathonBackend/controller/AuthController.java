package com.example.HackathonBackend.controller;

import com.example.HackathonBackend.dto.SignupRequestDto;
import com.example.HackathonBackend.dto.SignupResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @GetMapping("test")
    public String test(){
        return "String";
    }



}
