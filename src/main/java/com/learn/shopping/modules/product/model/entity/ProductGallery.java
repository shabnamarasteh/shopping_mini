package com.learn.shopping.modules.product.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PR_GALLERY")
public class ProductGallery implements Serializable {
    @Id
    @Column(name = "ID", columnDefinition = "number")
    @SequenceGenerator(name = "pr_gallery_seq", sequenceName = "pr_gallery_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pr_gallery_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", foreignKey = @ForeignKey(name = "product_gallery_fk_product"))
    private Product product;

    @Column(name = "IMAGE_SRC", columnDefinition = "nvarchar2(200)")
    private String imageSrc;

    public ProductGallery() {
    }

    public ProductGallery(Long id, Product product, String imageSrc) {
        this.id = id;
        this.product = product;
        this.imageSrc = imageSrc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
