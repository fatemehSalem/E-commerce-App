package com.micro.product.repository;

import com.micro.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdOrderByIdAsc(List<Long> productIds);

  //  List<Product> findAllByIdOrderByIdDesc(List<Long> productIds);
}
