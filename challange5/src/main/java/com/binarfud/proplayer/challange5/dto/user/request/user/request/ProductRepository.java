package com.binarfud.proplayer.challange5.dto.user.request;

import com.binarfud.proplayer.challange5.models.Products;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<com.binarfud.proplayer.challange5.dto.user.request.Products, UUID> {
    @Transactional
    @Modifying
    @Query("UPDATE Products p SET p.productName = :updateNameProduct WHERE p.id = :productId")
    void updateProductNameById(UUID productId, String updateNameProduct);
}