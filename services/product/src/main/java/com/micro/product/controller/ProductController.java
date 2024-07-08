package com.micro.product.controller;

import com.micro.product.model.*;
import com.micro.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody @Valid ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @PostMapping("/purchase")
    public ResponseEntity<ApiResponse<List<ProductPurchaseResponse>>> purchaseProducts(
            @RequestBody  @Valid  List<ProductPurchaseRequest> request
    ) {
        return productService.purchaseProducts(request);
    }
}
