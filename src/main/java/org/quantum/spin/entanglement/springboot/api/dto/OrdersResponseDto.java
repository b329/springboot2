package org.quantum.spin.entanglement.springboot.api.dto;

import lombok.Getter;
import org.quantum.spin.entanglement.springboot.domain.model.Orders;

import java.time.LocalDateTime;

@Getter
public class OrdersResponseDto {
    private  Long id;
    private  String orderNumber;
    private  String productNo;

    private LocalDateTime createdDate;

    public OrdersResponseDto(Orders entity) {
        this.id = entity.getId();
        this.orderNumber = entity.getOrderNumber();
        this.productNo = entity.getProductNo();
        this.createdDate = entity.getCreateDate();

    }

}
