package com.demisco.digishop.controller.admin;

import com.demisco.digishop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.security.Principal;

@Controller
@RequestMapping("/admin/warehouse")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class WarehouseAdmin {

    @Autowired
    PersonRepository personRepository;

    @GetMapping
    public String adminPage(Principal principal){

        System.out.println(principal.getName());

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return "admin/addWarehouse";
    }
}
