package org.quantum.spin.entanglement.springboot.service.orders;

import lombok.RequiredArgsConstructor;
import org.quantum.spin.entanglement.springboot.api.dto.*;
import org.quantum.spin.entanglement.springboot.domain.orders.Orders;
import org.quantum.spin.entanglement.springboot.domain.orders.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
@Autowired 가 없는 이유는 빈을 주입받는 방식에
@Autowired setter 생성자 주입방식이 있는데
여기서는 Controller 와 Service 에서 lombok 의 @RequiredArgsConstructor 로 생성자 Bean 을 주입받기 때문에
Autowired 가 없다.
생성자를 직접 안쓰고 lombok 을 사용하는 이점은 해당 클래스의 의존성 관계가 변경될때마다 생성자코드를 계속해서 수정해야 하는 번거로움을
피하기 위해서이다.
*/

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Transactional
    public Long save(OrdersSaveRequestDto requestDto) {
        return ordersRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, OrdersUpdateRequestDto requestDto) {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id="+ id));

        orders.update(requestDto.getProductNo());
                return id;
    }

    @Transactional
    public void delete(Long id) {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id="+ id));

        ordersRepository.delete(orders);

    }

    @Transactional(readOnly = true)
    public List<OrdersListResponseDto> findAllDesc() {
        return ordersRepository.findAllDesc().stream()
                .map(OrdersListResponseDto::new)
                .collect(Collectors.toList());

    }


    public OrdersResponseDto findById(Long id) {
        Orders entity = ordersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));
        return new OrdersResponseDto(entity);
    }

}
