package com.demisco.digishop.model;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {
    private Long productImageId;
    private byte[] productImage;
    // Only one image can be the default view. This will be the image show in the main catalog.
    //'Y' for set the flag to default view Or 'N' for don't set
    private Character defaultViewFlag;
    private Product product;

    public ProductImage() {
    }

    public ProductImage(byte[] productImage, Character defaultViewFlag, Product product) {
        this.productImage = productImage;
        this.defaultViewFlag = defaultViewFlag;
        this.product = product;
    }

    @Column(name = "product_image_id")
    @Id
    public Long getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Long productImageId) {
        this.productImageId = productImageId;
    }

    @Column(name = "image")
    @Lob
    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    @Column(name = "default_view_flag")
    public Character getDefaultViewFlag() {
        return defaultViewFlag;
    }

    public void setDefaultViewFlag(Character defaultViewFlag) {
        this.defaultViewFlag = defaultViewFlag;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
