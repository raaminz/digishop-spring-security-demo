package com.demisco.digishop.repository;

import com.demisco.digishop.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Query("from ProductImage i where  i.defaultViewFlag= ?1 and i.product.productId=?2")
    ProductImage loadImage( Character defaultViewFlag,Long productId);
}
