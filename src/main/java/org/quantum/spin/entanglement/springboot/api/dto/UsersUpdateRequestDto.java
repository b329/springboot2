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
    private  String phoneNumber;
    private  String email;
    private  String gender;

    @Builder
    public UsersUpdateRequestDto(String name, String nickname, String password, String phoneNumber, String email, String gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }
}
