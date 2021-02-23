package org.quantum.spin.entanglement.springboot.domain.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT P FROM Orders P ORDER BY P.id DESC")
    List<Orders> findAllDesc();

}