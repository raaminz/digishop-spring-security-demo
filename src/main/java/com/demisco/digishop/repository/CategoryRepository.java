package com.demisco.digishop.repository;

import com.demisco.digishop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

     @Query(value = "select distinct t.category_id, c.category_name from PRODUCT_CATEGORIES_BASE t, CATEGORY_TRANSLATIONS c " +
             "where t.category_id = c.category_id and t.parent_category_id is not null", nativeQuery = true)
     List<Category> loadCategoryList();
}
