package com.demisco.digishop.controller;

import com.demisco.digishop.model.Category;
import com.demisco.digishop.model.Product;
import com.demisco.digishop.repository.CategoryRepository;
import com.demisco.digishop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
@Secured({"ROLE_USER" , "ROLE_ADMIN"})
public class ProductController {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "top_product")
    public String showTopProduct(Model model) {

        List resultList = productRepository.findTopProduct();
        List<Product> productList = new ArrayList<>();
        for (Object object : resultList) {
            Object[] objects = (Object[]) object;

            Long productId = ((BigDecimal) objects[0]).longValue();
            String productName = (String) objects[1];
            Double price = ((BigDecimal) objects[2]).doubleValue();
            String categoryName = (String) objects[3];
            Long categoryId = ((BigDecimal) objects[4]).longValue();
            //assign a object to category object
            Product product = new Product();
            product.setProductId(productId);
            product.setProductName(productName);
            product.setPrice(price);
            Category category = new Category();
            category.setCategoryId(categoryId);
            category.setCategoryName(categoryName);
            product.setCategory(category);
            productList.add(product);
        }
        model.addAttribute("productList", productList);
        return "product/top_product";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/search_product_page")
    public String showSearchProductPage(Model model) {
        List<Category> categoryList = categoryRepository.loadCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "product/search_product_page";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/search_product")
    public String SearchProduct(Model model, HttpServletResponse response,
                                @RequestParam(name = "product_name") String productName,
                                @RequestParam(name = "category_id") String categoryIdInString,
                                @RequestParam(name = "page_number") int pageNumber) throws IOException {
        List<Category> categoryList = categoryRepository.loadCategoryList();
        model.addAttribute("categoryList", categoryList);

        final int pageSize = 10;
        //validation for pageNumber
        if (pageNumber <= 0) {
            return "bad_request";
        }
        //PageRequest pageNumber argument start from zero and in UI page start from 1.sync page numbers.
        pageNumber = pageNumber - 1;
        long categoryId = -1;
        boolean categoryIdExist;
        boolean productNameExist;
        //select how doing search product
        if (categoryIdInString == null || categoryIdInString.equals("")
                || categoryIdInString.equals("none") || !categoryIdInString.matches("\\d+")) {
            categoryIdExist = false;
        } else {
            categoryId = Long.parseLong(categoryIdInString);
            categoryIdExist = true;
        }
        if (productName == null || productName.equals("") || productName.trim().equals("")) {
            productNameExist = false;
        } else {

            productNameExist = true;
        }
        List<Product> productList = null;
        int productCount = 0;
        if (productNameExist && categoryIdExist) {
            productList = productRepository.findByCategoryIdAndProductName(categoryId, productName, PageRequest.of(pageNumber, pageSize));
            productCount = productRepository.findByCategoryIdAndProductNameCount(categoryId, productName).size();
        } else if (!productNameExist && categoryIdExist) {
            productList = productRepository.findByCategoryId(categoryId, PageRequest.of(pageNumber, pageSize));
            productCount = productRepository.findByCategoryIdCount(categoryId).size();
        } else if (productNameExist && !categoryIdExist) {
            productList = productRepository.findByProductName(productName, PageRequest.of(pageNumber, pageSize));
            productCount = productRepository.findByProductNameCount(productName).size();
        } else {
            model.addAttribute("searchFiledIsEmpty", "true");
            return "product/search_product_page";
        }
        int rest = productCount % 10;
        productCount = productCount / 10;
        if (rest != 0) {
            productCount += 1;
        }
        model.addAttribute("productList", productList);
        model.addAttribute("productCount", productCount);
        return "product/search_product";
    }
}
