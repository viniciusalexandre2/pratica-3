package com.example.demo.controller.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.controller.model.service.Product;
@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private long nextId = 1;
    public List<Product> getAllProducts() {
        return products;
    }
    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
    public Optional<Product> getProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }
    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
        }
        return existingProduct;
    }
    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}