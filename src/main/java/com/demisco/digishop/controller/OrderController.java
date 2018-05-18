package com.demisco.digishop.controller;

import com.demisco.digishop.model.Product;
import com.demisco.digishop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
@Secured({"ROLE_USER" , "ROLE_ADMIN"})
public class OrderController {

    private ProductRepository productRepository;

    @Autowired
    public OrderController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "show_order")
    public String showOrderPAge(HttpServletRequest request, Model model) {
        List<Long> cart = (List<Long>) request.getSession().getAttribute("cart");
        List<Product> productList = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.valueOf(0.0);
        for (Long productId : cart) {
            Product product = productRepository.findByProductId(productId);
            totalPrice = totalPrice.add(BigDecimal.valueOf(product.getPrice()));
            productList.add(product);
        }
        model.addAttribute("productList", productList);
        model.addAttribute("totalPrice", totalPrice);
        return "order/show_order";
    }

    @RequestMapping(method = RequestMethod.GET, path = "cart")
    public String cartManager(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam(name = "action") String action,
                              @RequestParam(name = "product_id") Long productId,
                              @RequestParam(name = "redirect") String redirect) throws IOException {
        //Test validation of product ID
        boolean isProductIdExists = productRepository.existsById(productId);
        if (action == null || action.trim().equals("") ||
                redirect == null || redirect.trim().equals("")
                ||!isProductIdExists) {
            return "bad_request";
        }

        List<Long> cart = (List<Long>) request.getSession().getAttribute("cart");
        String view;
        if (action.equals("add")) {
            cart.add(productId);
            if (redirect.equals("search_product")) {
                view= "redirect:/product/" + redirect
                        + "?category_id=" + request.getParameter("category_id")
                        + "&product_name=" + request.getParameter("product_name")
                        + "&page_number=" + request.getParameter("page_number");
            } else if (redirect.equals("top_product")) {
                view= "redirect:/product/" + redirect;
            }else {
                cart.remove(productId);
                view= "bad_request";
            }
        } else if (action.equals("delete")) {
            cart.remove(productId);
            view= "redirect:/order/show_order";
        }else {
            view= "bad_request";
        }
        return view;
    }
}
