package com.learn.shopping.modules.product.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "ID", columnDefinition = "number")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long id;
    @Column(name = "TITLE", columnDefinition ="nvarchar2(200)")
    private String title;
    @Transient
    @JsonIgnore
    private MultipartFile image;
    @Column(name = "imgSrc", columnDefinition ="nvarchar2(200)")
    private String imgSrc;
    @Column(name = "price", columnDefinition ="number")
    private Long price;
    @Column(name = "pricePromotion", columnDefinition ="number")
    private Long pricePromotion;
    @Column(name = "count", columnDefinition ="number")
    private Long count;
    @Column(name = "saleCount", columnDefinition ="number")
    private Long saleCount = 0L;

    public Product() {
    }

    public Product(Long id, String title, MultipartFile image, String imgSrc, Long price, Long pricePromotion, Long count, Long saleCount) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.imgSrc = imgSrc;
        this.price = price;
        this.pricePromotion = pricePromotion;
        this.count = count;
        this.saleCount = saleCount;
    }

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Product setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public Product setImage(MultipartFile image) {
        this.image = image;
        return this;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public Product setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Product setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Long getPricePromotion() {
        return pricePromotion;
    }

    public Product setPricePromotion(Long pricePromotion) {
        this.pricePromotion = pricePromotion;
        return this;
    }

    public Long getCount() {
        return count;
    }

    public Product setCount(Long count) {
        this.count = count;
        return this;
    }

    public Long getSaleCount() {
        return saleCount;
    }

    public Product setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
        return this;
    }
}