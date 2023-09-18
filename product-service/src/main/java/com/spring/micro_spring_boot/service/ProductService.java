package com.spring.micro_spring_boot.service;

import com.spring.micro_spring_boot.dto.ProductRequest;
import com.spring.micro_spring_boot.dto.ProductResponse;
import com.spring.micro_spring_boot.model.Product;
import com.spring.micro_spring_boot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {



    private final ProductRepository repository;

    public void createProduct(ProductRequest request){
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        repository.save(product);
        log.info("product {} is saved  ", product.getId());
    }

    public List<ProductResponse> getAllProduct() {

        List<Product> products = repository.findAll();
        return
                products.stream()
                        .map(this::mapToProductResponse)
                        .toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();
    }
}
