package com.mertalptekin.springbootrestapp.infra.repository;

import com.mertalptekin.springbootrestapp.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
}
