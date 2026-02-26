package com.example.HackathonBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Double totalAmount;
    private String status;
    private LocalDateTime orderDate;
    private String address;
    private List<OrderItemResponseDto> orderItems;
}
