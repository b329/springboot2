package org.quantum.spin.entanglement.springboot.api.dto;

import lombok.Getter;
import org.quantum.spin.entanglement.springboot.domain.model.Users;

import java.time.LocalDateTime;

@Getter
public class UsersListResponseDto {
    private  Long id;
    private  String name;
    private  String nickname;
    private  String password;
    private  String phoneNumber;
    private  String email;
    private  String gender;

    private LocalDateTime modifiedDate;

    public UsersListResponseDto(Users entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.nickname = entity.getNickname();
        this.password = entity.getPassword();
        this.phoneNumber = entity.getPhoneNumber();
        this.email = entity.getEmail();
        this.gender = entity.getGender();

        this.modifiedDate = entity.getModifiedDate();

    }

}
