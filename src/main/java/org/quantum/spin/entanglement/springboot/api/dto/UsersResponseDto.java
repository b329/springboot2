package org.quantum.spin.entanglement.springboot.api.dto;

import lombok.Getter;
import org.quantum.spin.entanglement.springboot.domain.users.Users;

import java.time.LocalDateTime;

@Getter
public class UsersResponseDto {
    private  Long id;
    private  String name;
    private  String nickname;
    private  String password;
    private  Integer phoneNumber;
    private  String gender;

    private LocalDateTime modifiedDate;

    public UsersResponseDto(Users entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.nickname = entity.getNickname();
        this.password = entity.getPassword();
        this.phoneNumber = entity.getPhoneNumber();
        this.gender = entity.getGender();

        this.modifiedDate = entity.getModifiedDate();

    }

}
