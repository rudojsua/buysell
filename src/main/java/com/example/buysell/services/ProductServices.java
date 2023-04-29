package com.example.buysell.services;

import com.example.buysell.model.Product;
import com.example.buysell.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServices {
    private final ProductRepository productRepository;


    public List<Product> listProducts(String title) {

        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void save(Product product) {
        log.info("Save new{} ", product);
        productRepository.save(product);
    }

    public void deleteProduct(long id) {
        log.info("Delete product with id: ",id);
        productRepository.deleteById(id);
    }


    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }
}
