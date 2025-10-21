package com.codexdei.springmvc.productwebapp.productwebapp.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codexdei.springmvc.productwebapp.productwebapp.models.dto.ProductDto;

@Controller
@RequestMapping("/util")
public class DetailProductController {

    @Value("${config.welcome}")
    private String message;

    @Value("${config.tax}") 
    private Double tax;

    private final List<ProductDto> productList = Arrays.asList(
    new ProductDto(1L, "Lenovo", "Computer", 500.0),
    new ProductDto(2L, "Samsung A12", "Cellphone", 500.0),
    new ProductDto(3L, "Asus", "Computer", 500.0),
    new ProductDto(4L, "Apple Watch SE", "Smartwatch", 300.0),
    new ProductDto(5L, "iPad Air", "Tablet", 600.0),
    new ProductDto(6L, "Logitech MX Master 3", "Accessory", 100.0),
    new ProductDto(7L, "Dell XPS 13", "Computer", 1200.0),
    new ProductDto(8L, "Xiaomi Redmi Note 11", "Cellphone", 250.0),
    new ProductDto(9L, "Samsung Galaxy Tab S7", "Tablet", 550.0),
    new ProductDto(10L, "Garmin Venu Sq", "Smartwatch", 200.0),
    new ProductDto(11L, "Kingston 1TB SSD", "Accessory", 90.0),
    new ProductDto(12L, "Motorola G73", "Cellphone", 350.0),
    new ProductDto(13L, "HP Envy", "Computer", 800.0)
);

    @GetMapping("/product/{id}")
    public String idProductDto(ModelMap model, @PathVariable Long id){

        model.addAttribute("title", "Detail ProductDto");

        Optional<ProductDto> findProductDto = getFindProductDto(id);

        model.addAttribute("product",findProductDto.orElse(null));

        return "details";
    }

  
    @GetMapping("/total/{id}")
    public String totalProductDto(Model model, @PathVariable Long id){

        Optional<ProductDto> findProductDto = getFindProductDto(id);
        Double total = findProductDto.get().getPrice() +  ((findProductDto.get().getPrice() * tax) / 100);

        if (findProductDto.isEmpty()) {
            
            model.addAttribute("error", "ProductDto with '" + id + "' not found");
        }

        ProductDto product = findProductDto.get();

        model.addAttribute("message", message);
        model.addAttribute("product", product);
        model.addAttribute("tax", tax);
        model.addAttribute("total", total);

        return "calculator";
    }

      @ModelAttribute("products")
    public List<ProductDto> productsModel(){
        
        return productList;
    }
    
    private Optional<ProductDto> getFindProductDto(Long id) {
        Optional<ProductDto> findProductDto = productList.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
        return findProductDto;
    }

}
