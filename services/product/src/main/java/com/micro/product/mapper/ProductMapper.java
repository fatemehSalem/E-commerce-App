package com.micro.product.mapper;

import com.micro.product.model.*;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductRequest productRequest) {
        return Product
                .builder()
                .id(productRequest.id())
                .availableQuantity(productRequest.availableQuantity())
                .category(Category
                        .builder().
                        id(productRequest.
                                categoryId()).
                        build())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
    }

    public ProductResponse fromProduct(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription());
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(product.getId() ,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity);

    }
}
