package com.platformN.productserviceplatformN.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.platformN.productserviceplatformN.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
