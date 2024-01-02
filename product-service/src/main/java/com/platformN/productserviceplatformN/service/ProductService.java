package com.platformN.productserviceplatformN.service;

import java.util.List;

import com.platformN.productserviceplatformN.dto.ProductRequest;
import com.platformN.productserviceplatformN.dto.ProductResponse;

public interface ProductService {
	
	public void createProduct(ProductRequest productRequest);
	public List<ProductResponse> getAllProducts();
	
}