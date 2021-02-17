package org.quantum.spin.entanglement.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String nickname;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private Integer phoneNumber;

    @Column(length = 100, nullable = true)
    private String gender;

    @Builder
    public Users(String name, String nickname, String password, Integer phoneNumber, String gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
}
