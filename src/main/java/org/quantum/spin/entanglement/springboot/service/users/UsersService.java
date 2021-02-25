package org.quantum.spin.entanglement.springboot.service.users;

import lombok.RequiredArgsConstructor;
import org.quantum.spin.entanglement.springboot.api.dto.UsersListResponseDto;
import org.quantum.spin.entanglement.springboot.api.dto.UsersResponseDto;
import org.quantum.spin.entanglement.springboot.api.dto.UsersSaveRequestDto;
import org.quantum.spin.entanglement.springboot.api.dto.UsersUpdateRequestDto;
import org.quantum.spin.entanglement.springboot.domain.users.Users;
import org.quantum.spin.entanglement.springboot.domain.users.UsersRepository;
import org.quantum.spin.entanglement.springboot.security.JwtTokenProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
public class UsersService {
    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Long save(UsersSaveRequestDto requestDto) {
        return usersRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long saveJoin(UsersSaveRequestDto requestDto) {
        return usersRepository.save(
                Users.builder()
                 .name(requestDto.toEntity().getName())
                 .nickname(requestDto.toEntity().getNickname())
                 .password(passwordEncoder.encode(requestDto.toEntity().getPassword()))
                 .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                 .build()).getId();
    }

    @Transactional
    public Long update(Long id, UsersUpdateRequestDto requestDto) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id="+ id));

        users.update(requestDto.getName(), requestDto.getNickname(), requestDto.getPassword(), requestDto.getPhoneNumber(), requestDto.getGender());

                return id;
    }

    @Transactional
    public void delete(Long id) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id="+ id));

        usersRepository.delete(users);

    }

    @Transactional(readOnly = true)
    public List<UsersListResponseDto> findAllDesc() {
        return usersRepository.findAllDesc().stream()
                .map(UsersListResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public Page<Users> retrievePageUsers(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }


    public UsersResponseDto findById(Long id) {
        Users entity = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));
        return new UsersResponseDto(entity);
    }

    public Users testUser() {

        Users testUser = new Users();
        testUser.setName("testName");
        testUser.setNickname("testNickname");
        testUser.setGender("testGender");
        testUser.setPassword("testPassword");

        return testUser;
    }
}
