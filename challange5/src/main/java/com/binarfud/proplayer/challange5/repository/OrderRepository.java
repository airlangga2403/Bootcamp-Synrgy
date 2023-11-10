package com.binarfud.proplayer.challange5.repository;

import com.binarfud.proplayer.challange5.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID> {
    @Query(value = "SELECT o.*, " +
            "FLOOR((EXTRACT(DAY FROM o.order_time) - 1) / 7) + 1 AS week_number, " +
            "EXTRACT(MONTH FROM o.order_time) AS month_number " +
            "FROM orders o " +
            "ORDER BY o.order_time", nativeQuery = true)
    List<Object[]> findAllWithWeekAndMonthNumbers();
}
