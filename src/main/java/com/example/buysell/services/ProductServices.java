package com.example.buysell.services;

import com.example.buysell.model.Image;
import com.example.buysell.model.Product;
import com.example.buysell.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;

        if(file1.getSize()!=0){
            image1=toImageEntiy(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if(file2.getSize()!=0){
            image2=toImageEntiy(file1);
            product.addImageToProduct(image2);
        }
        if(file3.getSize()!=0){
            image3=toImageEntiy(file1);
            product.addImageToProduct(image3);
        }

        log.info("Save new Product. Title: {}; Author: {} ", product.getTitle(), product.getAuthor());
        Product productromDb= productRepository.save(product);
        productromDb.setPreviewImageID(product.getImages().get(0).getId());
        productRepository.save(product);
    }

    private Image toImageEntiy(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFilesName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
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
