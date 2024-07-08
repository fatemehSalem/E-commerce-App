package com.micro.product.service;

import com.micro.product.mapper.ProductMapper;
import com.micro.product.model.ApiResponse;
import com.micro.product.model.ProductRequest;
import com.micro.product.model.ProductResponse;
import com.micro.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(ProductRequest productRequest){
        var product = productRepository.save(productMapper.toProduct(productRequest));
        var productResponse = productMapper.fromProduct(product);
        ApiResponse<ProductResponse> response = new ApiResponse<>(
                productResponse,
                "create Product was successful",
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
