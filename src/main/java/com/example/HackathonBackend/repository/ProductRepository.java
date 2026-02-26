package com.example.HackathonBackend.repository;

import com.example.HackathonBackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByCategory(String category);

    List<Product> findByIsAvailable(Boolean isAvailable);

    List<Product> findByNameContainingIgnoreCase(String name);
}
