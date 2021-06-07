package com.learn.shopping.modules.product.model.repository;

import com.learn.shopping.modules.product.model.entity.Product;
import com.learn.shopping.modules.product.model.entity.ProductGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGalleryRepository extends JpaRepository<ProductGallery, Long> {
    List<ProductGallery> findAllByProduct(Product product);
}
