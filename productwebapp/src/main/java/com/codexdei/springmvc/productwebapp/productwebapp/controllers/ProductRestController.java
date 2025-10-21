package com.codexdei.springmvc.productwebapp.productwebapp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codexdei.springmvc.productwebapp.productwebapp.models.Product;
import com.codexdei.springmvc.productwebapp.productwebapp.models.dto.ProductDto;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    // Contador para los IDs
    private static long idCounter = 1;

    @Value("#{${config.categories}}")
    private Map<String, Object> categories;

    private List<ProductDto> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(new ProductDto(idCounter++, "Lenovo", categories.get("computer").toString(), 500.0));
        products.add(new ProductDto(idCounter++, "Samsung A12", categories.get("cellphone").toString(), 500.0));
        products.add(new ProductDto(idCounter++, "Asus", categories.get("computer").toString(), 500.0));
        products.add(new ProductDto(idCounter++, "Apple Watch SE", categories.get("smartwatch").toString(), 300.0));
        products.add(new ProductDto(idCounter++, "iPad Air", categories.get("tablet").toString(), 600.0));
        products.add(
                new ProductDto(idCounter++, "Logitech MX Master 3", categories.get("accessory").toString(), 100.0));
        products.add(new ProductDto(idCounter++, "Dell XPS 13", categories.get("computer").toString(), 1200.0));
        products.add(
                new ProductDto(idCounter++, "Xiaomi Redmi Note 11", categories.get("cellphone").toString(), 250.0));
        products.add(new ProductDto(idCounter++, "Samsung Galaxy Tab S7", categories.get("tablet").toString(), 550.0));
        products.add(new ProductDto(idCounter++, "Garmin Venu Sq", categories.get("smartwatch").toString(), 200.0));
        products.add(new ProductDto(idCounter++, "Kingston 1TB SSD", categories.get("accessory").toString(), 90.0));
        products.add(new ProductDto(idCounter++, "Motorola G73", categories.get("cellphone").toString(), 350.0));
        products.add(new ProductDto(idCounter++, "HP Envy", categories.get("computer").toString(), 800.0));
    }

    @GetMapping
    public List<ProductDto> listJson() {

        return products;
    }

    @PostMapping
    public ProductDto createProductDto(@RequestBody ProductDto productDto) {

        productDto.setId(idCounter++);
        products.add(productDto);

        return productDto;
    }

    @GetMapping("/{category}/{myprice}")
    public ResponseEntity priceCategory(@PathVariable String category, @PathVariable Double myprice) {

        List<ProductDto> listProdCatePrice = products.stream()
                .filter(p -> p.getCategory().equals(category) && (p.getPrice() >= myprice))
                .collect(Collectors.toList());

        if (listProdCatePrice.isEmpty()) {

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listProdCatePrice);
    }

    @GetMapping("/var")
    public Optional<ProductDto> findName(
            @RequestParam(required = false, defaultValue = "No name provided") String name) {

        Optional<ProductDto> product = products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();

        return product;
    }

}
