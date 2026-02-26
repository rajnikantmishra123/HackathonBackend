package com.example.HackathonBackend.repository;

import com.example.HackathonBackend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);

    List<CartItem> findByProductId(Long productId);
}
