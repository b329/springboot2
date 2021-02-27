package org.quantum.spin.entanglement.springboot.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quantum.spin.entanglement.springboot.domain.model.Users;

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
    private  String name;
    private  String nickname;
    private  String password;
    private  Integer phoneNumber;
    private  String gender;

    @Builder
    public UsersSaveRequestDto(String name, String nickname, String password, Integer phoneNumber, String gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public Users toEntity() {
        return Users.builder()
                    .name(name)
                    .nickname(nickname)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .gender(gender)
                    .build();
    }
}
