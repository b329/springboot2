package org.quantum.spin.entanglement.springboot.api;

import lombok.RequiredArgsConstructor;
import org.quantum.spin.entanglement.springboot.api.dto.UsersListResponseDto;
import org.quantum.spin.entanglement.springboot.api.dto.UsersResponseDto;
import org.quantum.spin.entanglement.springboot.api.dto.UsersSaveRequestDto;
import org.quantum.spin.entanglement.springboot.api.dto.UsersUpdateRequestDto;
import org.quantum.spin.entanglement.springboot.domain.users.Users;
import org.quantum.spin.entanglement.springboot.domain.users.UsersRepository;
import org.quantum.spin.entanglement.springboot.security.JwtTokenProvider;
import org.quantum.spin.entanglement.springboot.service.users.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsersRepository usersRepository;

    // 회원가입
    @PostMapping("/api/v1/join")
    public Long join(@RequestBody UsersSaveRequestDto requestDto) {
        return usersService.memberJoin(requestDto);
    }

    // 로그인: JWT 토큰을 획득.
    @PostMapping("/api/v1/login")
    public String login(@RequestBody UsersSaveRequestDto requestDto) {
        return usersService.memberLogin(requestDto);
    }

    // 단순저장
    @PostMapping("/api/v1/users")
    public Long save(@RequestBody UsersSaveRequestDto requestDto) {
        return usersService.save(requestDto);
    }

    // id 값으로 update
    @PutMapping("/api/v1/users/{id}")
    public Long update(@PathVariable Long id, @RequestBody UsersUpdateRequestDto requestDto) {
        return usersService.update(id, requestDto);
    }

    // id 값으로 삭제
    @DeleteMapping("/api/v1/users/{id}")
    public Long delete(@PathVariable Long id) {
        usersService.delete(id);
        return id;
    }

    // id 값으로 get
    @GetMapping("/api/v1/users/{id}")
    public UsersResponseDto findById (@PathVariable Long id) {
        return usersService.findById(id);
    }

    // user 전체 값
    @GetMapping("/api/v1/users")
    public List<UsersListResponseDto> retrieveUsers() {
        return usersService.findAllDesc();
    }

    // user 전체 값 paging 처리
    @GetMapping("/api/v1/users/pages")
    public Page<UsersResponseDto> retrievePageUsers(final Pageable pageable) {
        return usersService.retrievePageUsers(pageable).map(UsersResponseDto::new);
    }

    // API 호출이 되는지 확인하는 간단한 api
    @GetMapping("/api/v1/users/test")
    public Users userTest() {
        return usersService.testUser();
    }

}
