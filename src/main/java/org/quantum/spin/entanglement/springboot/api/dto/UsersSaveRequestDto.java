package org.quantum.spin.entanglement.springboot.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quantum.spin.entanglement.springboot.domain.model.Users;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/* Entity 클래스와 유사한 Dto 클래스를 추가로 생성한 이유는 Entity 클래스를 Request/Response 클래스로
*  사용하지 않기 위해사이다. Entity 클래스는 데이터베이스와 맞닿은 클래스이고
*  Entity 클래스를 변경하면 스키마 자체가 변경이 된다.
*  Entity 클래스가 변경이되면 여러 클래스에 영향을 끼치지만 Request 와 Response 용 Dto 는 view 를 위한 클래스라
*  자주 변경이 필요한 경우가 있다.
*  결론적으로 ViewLayer 와 DB layer 와 분리하는 것이 바람직하고
*  Controller 에서는 결과 값으로 여러 테이블을 조인해서 사용하는 경우가 빈번하다. */

@Getter
@NoArgsConstructor

public class UsersSaveRequestDto {

    @NotEmpty(message = "이름에 공백을 넣을 수 없습니다.")
    @Size(max = 20, message = "이름은 최대 20자까지 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z]*$", message = "한글, 영문 대소문자만 가능합니다.")
    private  String name;

    @NotEmpty(message = "닉네임에 공백을 넣을 수 없습니다.")
    @Size(max = 30, message = "닉네임은 최대 30자까지 입력해주세요.")
    @Pattern(regexp = "^[a-z]*$", message = "영문 소문자만 가능합니다.")
    private  String nickname;

    @NotEmpty(message = "비밀번호에 공백을 넣을 수 없습니다.")
    @Size(min = 10, message = "비밀번호는 최소 10자 이상 입력해주세요.")
    @Pattern(regexp = "^.*(?=^.{10,100}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함해야 합니다.")
    private  String password;

    @NotEmpty(message = "전화번호에 공백을 넣을 수 없습니다.")
    @Size(max = 20, message = "전화번호는 최대 20자까지 입력해주세요.")
    @Pattern(regexp = "^[0-9]*$", message = "0 에서 9 까지 숫자만 가능합니다.")
    private  String phoneNumber;

    @NotEmpty(message = "이메일에 공백을 넣을 수 없습니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "이메일 형식에 맞지 않습니다." )
    private  String email;

    private  String gender;

    @Builder
    public UsersSaveRequestDto(String name, String nickname, String password, String phoneNumber, String email, String gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }

    public Users toEntity() {
        return Users.builder()
                    .name(name)
                    .nickname(nickname)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .gender(gender)
                    .build();
    }
}
