package com.codexdei.springmvc.productwebapp.productwebapp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    // Contador para los IDs
private static long idCounter = 1;

// Variables individuales de tipo ProductDto
private ProductDto lenovo = new ProductDto(idCounter++, "Lenovo", "computer", 500.0);
private ProductDto samsungA12 = new ProductDto(idCounter++, "Samsung A12", "cellphone", 500.0);
private ProductDto asus = new ProductDto(idCounter++, "Asus", "computer", 500.0);
private ProductDto appleWatchSE = new ProductDto(idCounter++, "Apple Watch SE", "smartwatch", 300.0);
private ProductDto iPadAir = new ProductDto(idCounter++, "iPad Air", "tablet", 600.0);
private ProductDto logitechMXMaster3 = new ProductDto(idCounter++, "Logitech MX Master 3", "accessory", 100.0);
private ProductDto dellXPS13 = new ProductDto(idCounter++, "Dell XPS 13", "computer", 1200.0);
private ProductDto xiaomiRedmiNote11 = new ProductDto(idCounter++, "Xiaomi Redmi Note 11", "cellphone", 250.0);
private ProductDto samsungGalaxyTabS7 = new ProductDto(idCounter++, "Samsung Galaxy Tab S7", "tablet", 550.0);
private ProductDto garminVenuSq = new ProductDto(idCounter++, "Garmin Venu Sq", "smartwatch", 200.0);
private ProductDto kingstonSSD = new ProductDto(idCounter++, "Kingston 1TB SSD", "accessory", 90.0);
private ProductDto motorolaG73 = new ProductDto(idCounter++, "Motorola G73", "cellphone", 350.0);
private ProductDto hpEnvy = new ProductDto(idCounter++, "HP Envy", "computer", 800.0);

    private List<ProductDto> products = new ArrayList<>();

    {
    products.add(lenovo);
    products.add(samsungA12);
    products.add(asus);
    products.add(appleWatchSE);
    products.add(iPadAir);
    products.add(logitechMXMaster3);
    products.add(dellXPS13);
    products.add(xiaomiRedmiNote11);
    products.add(samsungGalaxyTabS7);
    products.add(garminVenuSq);
    products.add(kingstonSSD);
    products.add(motorolaG73);
    products.add(hpEnvy);
    }

    @GetMapping
    public List<ProductDto> listJson(){

        return products;
    }

    @PostMapping
    public ProductDto createProductDto(@RequestBody ProductDto productDto){

        productDto.setId(idCounter++);
        products.add(productDto);

        return productDto;
    }

    @GetMapping("/{category}/{myprice}")
    public ResponseEntity priceCategory(@PathVariable String category, @PathVariable Double myprice){

        List<ProductDto> listProdCatePrice = products.stream()
            .filter(p-> p.getCategory().equals(category) && (p.getPrice() >= myprice))
            .collect(Collectors.toList());

            if (listProdCatePrice.isEmpty()) {
                
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(listProdCatePrice);
    }

    @GetMapping("/var")
    public Optional<ProductDto> findName(@RequestParam(required = false,defaultValue = "not name") String name){

        Optional<ProductDto> product = products.stream()
            .filter(p -> p.getName().equalsIgnoreCase(name))
            .findFirst();

        return product;
    }

}
