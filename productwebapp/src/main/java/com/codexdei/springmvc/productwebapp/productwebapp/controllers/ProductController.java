package com.codexdei.springmvc.productwebapp.productwebapp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codexdei.springmvc.productwebapp.productwebapp.models.dto.ProductDto;

@Controller
@RequestMapping("/products")
public class ProductController {

    private List<ProductDto> productList = new ArrayList<>();

    private Long idCounter = 1L;

    @GetMapping("/new")
    public String showForm(Model model) {

        model.addAttribute("product", new ProductDto());

        return "product-form";
    }

    @GetMapping("/list")
    public String listProducts(Model model) {

        model.addAttribute("products", productList);

        return "product-list";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") ProductDto productDto, Model model) {

        // asignar id
        productDto.setId(idCounter++);

        // Guardar producto
        productList.add(productDto);

        // mostrar
        model.addAttribute("message", "Successful save");
        model.addAttribute("product", productDto);

        return "product-result";

    }

}
