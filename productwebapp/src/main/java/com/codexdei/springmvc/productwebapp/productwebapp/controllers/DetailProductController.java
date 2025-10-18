package com.codexdei.springmvc.productwebapp.productwebapp.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.codexdei.springmvc.productwebapp.productwebapp.models.Product;

@Controller
public class DetailProductController {

    private final List<Product> productList = Arrays.asList(

            new Product(1L,"Lenovo", "Computer",500.0),
            new Product(2L,"Samsung A12", "Cellphone",500.0),
            new Product(3L,"Asus", "Computer",500.0)
        );

    @GetMapping("/product/{id}")
    public String idProduct(ModelMap model, @PathVariable Long id){

        model.addAttribute("title", "Detail Product");

        Optional<Product> findProduct = productList.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();

        model.addAttribute("product",findProduct.orElse(null));

        return "details";
    }
    
    @ModelAttribute("products")
    public List<Product> productsModel(){

        return productList;
    }

    

    

}
