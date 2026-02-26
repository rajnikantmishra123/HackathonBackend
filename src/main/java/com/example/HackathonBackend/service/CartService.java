package com.example.HackathonBackend.service;

import com.example.HackathonBackend.dto.CartItemRequestDto;
import com.example.HackathonBackend.dto.CartItemResponseDto;
import com.example.HackathonBackend.dto.CartResponseDto;
import com.example.HackathonBackend.entity.Cart;
import com.example.HackathonBackend.entity.CartItem;
import com.example.HackathonBackend.entity.Product;
import com.example.HackathonBackend.entity.User;
import com.example.HackathonBackend.exception.ResourceNotFoundException;
import com.example.HackathonBackend.repository.CartItemRepository;
import com.example.HackathonBackend.repository.CartRepository;
import com.example.HackathonBackend.repository.ProductRepository;
import com.example.HackathonBackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository,
            UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CartResponseDto addItemToCart(Long userId, CartItemRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product not found with id: " + requestDto.getProductId()));

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setCreatedAt(LocalDateTime.now());
                    newCart.setCartItems(new ArrayList<>());
                    return cartRepository.save(newCart);
                });

        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(requestDto.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + requestDto.getQuantity());
            item.setSubtotal(item.getQuantity() * product.getPrice());
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(requestDto.getQuantity());
            newItem.setSubtotal(requestDto.getQuantity() * product.getPrice());
            cartItemRepository.save(newItem);
            cart.getCartItems().add(newItem);
        }

        return mapToDto(cart);
    }

    public CartResponseDto getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user id: " + userId));
        return mapToDto(cart);
    }

    private CartResponseDto mapToDto(Cart cart) {
        List<CartItemResponseDto> items = cart.getCartItems().stream()
                .map(item -> new CartItemResponseDto(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity(),
                        item.getSubtotal()))
                .collect(Collectors.toList());

        double totalAmount = items.stream()
                .mapToDouble(CartItemResponseDto::getSubtotal)
                .sum();

        return new CartResponseDto(
                cart.getId(),
                cart.getUser().getId(),
                cart.getCreatedAt(),
                items,
                totalAmount);
    }
}
