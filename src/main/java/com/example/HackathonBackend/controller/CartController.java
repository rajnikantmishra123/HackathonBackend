package com.example.HackathonBackend.controller;

import com.example.HackathonBackend.dto.CartItemRequestDto;
import com.example.HackathonBackend.dto.CartResponseDto;
import com.example.HackathonBackend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> addItemToCart(
            @RequestParam(defaultValue = "1") Long userId,
            @RequestBody CartItemRequestDto requestDto) {
        CartResponseDto cart = cartService.addItemToCart(userId, requestDto);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CartResponseDto> getCart(
            @RequestParam(defaultValue = "1") Long userId) {
        CartResponseDto cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }
}
