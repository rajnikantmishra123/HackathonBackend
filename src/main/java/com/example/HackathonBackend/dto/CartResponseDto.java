package com.example.HackathonBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
    private Long id;
    private Long userId;
    private LocalDateTime createdAt;
    private List<CartItemResponseDto> cartItems;
    private Double totalAmount;
}
