package com.spring.micro_spring_boot.controller;

import com.spring.micro_spring_boot.dto.ProductRequest;
import com.spring.micro_spring_boot.dto.ProductResponse;
import com.spring.micro_spring_boot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request){
        service.createProduct(request);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct(){
            return service.getAllProduct();
    }
}
