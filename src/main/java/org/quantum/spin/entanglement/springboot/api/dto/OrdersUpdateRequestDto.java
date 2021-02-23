package org.quantum.spin.entanglement.springboot.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrdersUpdateRequestDto {
    private  String orderNumber;
    private  String productNo;

    @Builder
    public OrdersUpdateRequestDto(String orderNumber, String productNo) {
        this.orderNumber = orderNumber;
        this.productNo = productNo;
    }
}
