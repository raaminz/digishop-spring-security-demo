package com.demisco.digishop.controller;

import com.demisco.digishop.model.ProductImage;
import com.demisco.digishop.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/product_image")
@Secured({"ROLE_USER" , "ROLE_ADMIN"})
public class ProductImageController {

    private ProductImageRepository productImageRepository;

    @Autowired
    public ProductImageController(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void loadProductImage(@RequestParam(name = "product_id") Long productId, HttpServletResponse response) throws IOException {
        ProductImage productImage = productImageRepository.loadImage('N', productId);
        if (productImage != null) {
            byte[] image = productImage.getProductImage();
            response.setContentLength(image.length);
            response.getOutputStream().write(image);
        }
    }
}
