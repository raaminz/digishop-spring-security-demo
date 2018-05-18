package com.demisco.digishop.repository;

import com.demisco.digishop.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select ProductsBase.PRODUCT_ID, ProductsBase.PRODUCT_NAME, ProductsBase.LIST_PRICE," +
            " CategoryTranslations.CATEGORY_NAME, CategoryTranslations.CATEGORY_ID" +
            " from products_base ProductsBase, CATEGORY_TRANSLATIONS CategoryTranslations, PRODUCT_TRANSLATIONS ProductTranslations " +
            "where CategoryTranslations.CATEGORY_ID = ProductsBase.CATEGORY_ID " +
            "and ProductTranslations.PRODUCT_ID = ProductsBase.PRODUCT_ID " +
            "and CategoryTranslations.LANGUAGE = 'EN' " +
            "and ProductTranslations.LANGUAGE = 'EN' " +
            "and ProductsBase.product_id in (select PRODUCT_ID from " +
            "(SELECT OrderItems.product_id, max(OrderItems.quantity) as Items_Ordered " +
            "FROM order_items OrderItems, products_base ProductsBase " +
            "WHERE ProductsBase.product_id = OrderItems.product_id " +
            "GROUP By OrderItems.product_id, ProductsBase.Product_Name, ProductsBase.category_id " +
            "ORDER BY Items_Ordered DESC))", nativeQuery = true)
    List findTopProduct();

    @Query("select distinct new Product (b.productId,b.productName,b.price,t.categoryId,t.categoryName) from Product b inner join" +
            " b.category t where upper(b.productName) like upper(concat('%', ?1,'%'))")
    List<Product> findByProductName(String productName, Pageable pageable);

    @Query("select distinct new Product (b.productId,b.productName,b.price,t.categoryId,t.categoryName) from Product b inner join" +
            " b.category t where upper(b.productName) like upper(concat('%', ?1,'%'))")
    List<Product> findByProductNameCount(String productName);

    @Query("select distinct new Product (b.productId,b.productName,b.price,t.categoryId,t.categoryName) from Product b inner join" +
            " b.category t where t.categoryId=?1")
    List<Product> findByCategoryId(Long categoryId, Pageable pageable);

    @Query("select distinct new Product (b.productId,b.productName,b.price,t.categoryId,t.categoryName) from Product b inner join" +
            " b.category t where t.categoryId=?1")
    List<Product> findByCategoryIdCount(Long categoryId);

    @Query("select distinct new Product (b.productId,b.productName,b.price,t.categoryId,t.categoryName) from Product b inner join" +
            " b.category t where t.categoryId=?1 and upper(b.productName) like upper(concat('%', ?2,'%'))")
    List<Product> findByCategoryIdAndProductName(Long categoryId, String productName, Pageable pageable);

    @Query("select distinct new Product (b.productId,b.productName,b.price,t.categoryId,t.categoryName) from Product b inner join" +
            " b.category t where t.categoryId=?1 and upper(b.productName) like upper(concat('%', ?2,'%'))")
    List<Product> findByCategoryIdAndProductNameCount(Long categoryId, String productName);

    @Query("select distinct new Product (b.productId,b.productName,b.price,t.categoryId,t.categoryName) from Product b inner join" +
            " b.category t where b.productId =?1")
    Product findByProductId(Long productId);
}
