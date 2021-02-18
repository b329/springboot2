package org.quantum.spin.entanglement.springboot.api;

import lombok.RequiredArgsConstructor;
import org.quantum.spin.entanglement.springboot.api.dto.UsersResponseDto;
import org.quantum.spin.entanglement.springboot.api.dto.UsersSaveRequestDto;
import org.quantum.spin.entanglement.springboot.api.dto.UsersUpdateRequestDto;
import org.quantum.spin.entanglement.springboot.domain.users.Users;
import org.quantum.spin.entanglement.springboot.service.users.UsersService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController

/** @Autowired 가 없는 이유는 빈을 주입받는 방식에
@Autowired, setter, 생성자 주압 방식이 있는데
여기서는 Controller 와 Service 에서 lombok 의 @RequiredArgsConstructor 로 생성자 Bean 을 주입받기 때문에
Autowired 가 없다.
생성자를 직접 안쓰고 lombok 을 사용하는 이점은 해당 클래스의 의존성 관계가 변경될때마다 생성자코드를 계속해서 수정해야 하는 번거로움을
피하기 위해서이다.
*/

public class UsersApiController {

    // @Autowired
    private final UsersService usersService;

    @PostMapping("/api/v1/users")
    public Long save(@RequestBody UsersSaveRequestDto requestDto) {
        return usersService.save(requestDto);
    }

    @PutMapping("/api/v1/users/{id}")
    public Long update(@PathVariable Long id, @RequestBody UsersUpdateRequestDto requestDto) {
        return usersService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/users/{id}")
    public Long delete(@PathVariable Long id) {
        usersService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/users/{id}")
    public UsersResponseDto findById (@PathVariable Long id) {
        return usersService.findById(id);
    }

    @GetMapping("/api/v1/users/test")
    public Users userTest() {
        return usersService.testUser();
    }

}
