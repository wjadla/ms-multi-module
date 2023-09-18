package com.spring.micro_spring_boot.repository;

import com.spring.micro_spring_boot.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String > {
}
