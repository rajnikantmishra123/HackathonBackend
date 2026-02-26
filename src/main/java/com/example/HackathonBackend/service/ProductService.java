package com.example.HackathonBackend.service;

import com.example.HackathonBackend.dto.ProductRequestDto;
import com.example.HackathonBackend.dto.ProductResponseDto;
import com.example.HackathonBackend.entity.Product;
import com.example.HackathonBackend.exception.ResourceNotFoundException;
import com.example.HackathonBackend.repository.ProductRepository;
import com.example.HackathonBackend.repository.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setCategory(requestDto.getCategory());
        product.setPrice(requestDto.getPrice());
        product.setStock(requestDto.getStock());
        product.setIsAvailable(requestDto.getIsAvailable() != null ? requestDto.getIsAvailable() : true);
        Product saved = productRepository.save(product);
        return mapToDto(saved);
    }

    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(this::mapToDto);
    }

    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return mapToDto(product);
    }

    public Page<ProductResponseDto> searchProducts(String name, String category,
            Double minPrice, Double maxPrice,
            Pageable pageable) {
        Specification<Product> spec = ProductSpecification.filterProducts(name, category, minPrice, maxPrice);
        return productRepository.findAll(spec, pageable).map(this::mapToDto);
    }

    private ProductResponseDto mapToDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getIsAvailable());
    }
}
