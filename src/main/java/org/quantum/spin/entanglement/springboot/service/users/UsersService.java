package org.quantum.spin.entanglement.springboot.service.users;

import lombok.RequiredArgsConstructor;
import org.quantum.spin.entanglement.springboot.api.dto.UsersListResponseDto;
import org.quantum.spin.entanglement.springboot.api.dto.UsersResponseDto;
import org.quantum.spin.entanglement.springboot.api.dto.UsersSaveRequestDto;
import org.quantum.spin.entanglement.springboot.api.dto.UsersUpdateRequestDto;
import org.quantum.spin.entanglement.springboot.domain.model.Users;
import org.quantum.spin.entanglement.springboot.domain.model.UsersRepository;
import org.quantum.spin.entanglement.springboot.mapper.UsersMapper;
import org.quantum.spin.entanglement.springboot.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

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
    // Mybatis 를 이용한 데이터 처리
    @Autowired
    private UsersMapper usersMapper;

    @Transactional
    public void memberJoin(UsersSaveRequestDto requestDto) {

        boolean member = usersRepository.findByNickname(requestDto.toEntity().getNickname())
                .isPresent();
        if (member) {
            throw new IllegalArgumentException("이미 가입된 닉네임 입니다.");
        }

        usersRepository.save(
                Users.builder()
                        .name(requestDto.toEntity().getName())
                        .nickname(requestDto.toEntity().getNickname())
                        .password(passwordEncoder.encode(requestDto.toEntity().getPassword()))
                        .phoneNumber(requestDto.toEntity().getPhoneNumber())
                        .email(requestDto.toEntity().getEmail())
                        .gender(requestDto.toEntity().getGender())
                        .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                        .build()).getId();

    }

    @Transactional
    public String memberLogin(UsersSaveRequestDto requestDto) {
        Users member = usersRepository.findByNickname(requestDto.toEntity().getNickname())
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 nickname 입니다."));
            if (!passwordEncoder.matches(requestDto.toEntity().getPassword(), member.getPassword())) {
                throw new IllegalArgumentException("잘못된 비밀번호입니다.");
            }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }

    @Transactional
    public Long save(UsersSaveRequestDto requestDto) {
        return usersRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, UsersUpdateRequestDto requestDto) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id="+ id));

        users.update(requestDto.getName(), requestDto.getNickname(), requestDto.getPassword(), requestDto.getPhoneNumber(), requestDto.getEmail(), requestDto.getGender());

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

    @Transactional(readOnly = true)
    public UsersResponseDto findById(Long id) {
        Users entity = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));
        return new UsersResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public UsersResponseDto findByName(String name) {
        Users entity = usersRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. name=" + name));
        return new UsersResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public UsersResponseDto findByEmail(String email) {
        Users entity = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. email=" + email));
        return new UsersResponseDto(entity);
    }


    /** Mybatis 를 이용한 데이터 조회 **/
    @Transactional(readOnly = true)
    public UsersResponseDto findById2(Long id) {
        Users entity = usersMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));
        return new UsersResponseDto(entity);
    }

    public Users testUser() {

        Users testUser = new Users();
        testUser.setName("testName");
        testUser.setNickname("testNickname");
        testUser.setEmail("testEmail");
        testUser.setPassword("testPassword");

        return testUser;
    }
}
