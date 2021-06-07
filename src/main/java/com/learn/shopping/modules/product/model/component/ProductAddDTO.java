package com.learn.shopping.modules.product.model.component;

import com.learn.shopping.modules.product.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class ProductAddDTO {
    private String title;
    private MultipartFile imgSrc;
    private Long price;
    private Long pricePromotion;
    private Long count;

    public ProductAddDTO() {
    }

    public ProductAddDTO(String title, MultipartFile imgSrc, Long price, Long pricePromotion, Long count) {
        this.title = title;
        this.imgSrc = imgSrc;
        this.price = price;
        this.pricePromotion = pricePromotion;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public ProductAddDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getImgSrc() {
        return imgSrc;
    }

    public ProductAddDTO setImgSrc(MultipartFile imgSrc) {
        this.imgSrc = imgSrc;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public ProductAddDTO setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Long getPricePromotion() {
        return pricePromotion;
    }

    public ProductAddDTO setPricePromotion(Long pricePromotion) {
        this.pricePromotion = pricePromotion;
        return this;
    }

    public Long getCount() {
        return count;
    }

    public ProductAddDTO setCount(Long count) {
        this.count = count;
        return this;
    }

    public Product convert2Object() {
        Product product = new Product();
        if(this.title != null) product.setTitle(this.title);
        if(this.imgSrc != null) product.setImage(this.imgSrc);
        if(this.price != null) product.setPrice(this.price);
        if(this.pricePromotion != null) product.setPricePromotion(this.pricePromotion);
        if(this.count != null) product.setCount(this.count);
        return product;
    }
}