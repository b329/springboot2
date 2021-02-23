package org.quantum.spin.entanglement.springboot.api.dto;

import lombok.Getter;
import org.quantum.spin.entanglement.springboot.domain.orders.Orders;

import java.time.LocalDateTime;

@Getter
public class OrdersListResponseDto {
    private  Long id;
    private  String orderNumber;
    private  String productNo;

    private LocalDateTime createdDate;

    public OrdersListResponseDto(Orders entity) {
        this.id = entity.getId();
        this.orderNumber = entity.getOrderNumber();
        this.productNo = entity.getProductNo();

        this.createdDate = entity.getCreateDate();

    }

}
