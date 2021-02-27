package org.quantum.spin.entanglement.springboot.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 12, nullable = false)
    private String orderNumber;

    @Column(length = 100, nullable = false)
    private String productNo;

    @Builder
    public Orders(String orderNumber, String productNo) {
        this.orderNumber = orderNumber;
        this.productNo = productNo;
    }

    /* Jpa 의 영속성 컨텍스트 떄문에 update 기능에서 데이터베이스 쿼이를 날리는 부분이 없다.
     *  영속성 컨텍스트 : 엔티티를 영구 저장하는 환경.
     *  Jpa 의 핵심내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.
     *  쿼리를 날릴 필요가 없는 이 부분을 Dirty Cheking 이라고도 함. */

    public void update(String productNo) {
        this.productNo = productNo;
    }

}
