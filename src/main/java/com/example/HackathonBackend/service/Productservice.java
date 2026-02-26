package com.example.HackathonBackend.service;

import com.example.HackathonBackend.entity.Product;
import com.example.HackathonBackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Productservice {

    @Autowired
    private ProductRepository productRepository;

    // 🔹 Add Product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // 🔹 Get All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 🔹 Get Product By ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    // 🔹 Update Product
    public Product updateProduct(Long id, Product updatedProduct) {

        Product existingProduct = getProductById(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        existingProduct.setIsAvailable(updatedProduct.getIsAvailable());

        return productRepository.save(existingProduct);
    }

    // 🔹 Delete Product
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}