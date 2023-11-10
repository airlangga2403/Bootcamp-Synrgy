package com.binarfud.proplayer.challange5.repository;

import com.binarfud.proplayer.challange5.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Products, UUID> {
}
