package com.micro.product.service;

import com.micro.product.exception.ProductPurchaseException;
import com.micro.product.mapper.ProductMapper;
import com.micro.product.model.*;
import com.micro.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
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

    public ResponseEntity<ApiResponse<List<ProductPurchaseResponse>>> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request.stream()
                .map(ProductPurchaseRequest::productId).toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size())
            throw new ProductPurchaseException("One or more products does not exits!");

        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity())
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        ApiResponse<List<ProductPurchaseResponse>> response = new ApiResponse<>(
                purchasedProducts,
                "purchase Products were successful",
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<ApiResponse<ProductResponse>> findById(Long productId) {
        var productResponse = productRepository.findById(productId)
                .map(productMapper::fromProduct)
                .orElseThrow(() -> new EntityNotFoundException("product not found with the ID:: " + productId));

        ApiResponse<ProductResponse> response = new ApiResponse<>(
                productResponse,
                "Product found By Id was successful",
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
