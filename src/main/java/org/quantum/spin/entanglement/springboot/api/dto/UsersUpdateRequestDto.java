package org.quantum.spin.entanglement.springboot.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersUpdateRequestDto {
    private  String name;
    private  String nickname;
    private  String password;
    private  Integer phoneNumber;
    private  String gender;

    @Builder
    public UsersUpdateRequestDto(String name, String nickname, String password, Integer phoneNumber, String gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
}
