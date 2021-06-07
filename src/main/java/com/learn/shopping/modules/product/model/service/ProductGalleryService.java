package com.learn.shopping.modules.product.model.service;

import com.learn.shopping.modules.product.model.entity.Product;
import com.learn.shopping.modules.product.model.entity.ProductGallery;
import com.learn.shopping.modules.product.model.repository.ProductGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGalleryService {
    private ProductGalleryRepository productGalleryRepository;

    @Autowired
    public ProductGalleryService(ProductGalleryRepository productGalleryRepository) {
        this.productGalleryRepository = productGalleryRepository;
    }

    public List<ProductGallery> findAllByProduct(Product product) {
        return this.productGalleryRepository.findAllByProduct(product);
    }

    public void deleteAll(List<ProductGallery> productGalleryList) {
        this.productGalleryRepository.deleteAll(productGalleryList);
    }
}
