package com.demisco.digishop.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products_base")
public class Product {
    private Long productId;
    private String productName;
    private Double price;
    private Category category;
    private Long parentCategoryId;
    private List<ProductImage> productImageList;

    public Product() {
    }

    public Product(Long productId) {
        this.productId = productId;
    }

    public Product(Long productId, String productName, Double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public Product(Long productId, String productName, Double price, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public Product(Long productId, String productName, Double price, Long categoryId, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category=new Category(categoryId,categoryName);
    }

    @Id
    @Column(name = "product_id")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name = "list_price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "parent_category_id")
    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    @OneToMany(mappedBy = "product")
    public List<ProductImage> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImage> productImageList) {
        this.productImageList = productImageList;
    }
}
