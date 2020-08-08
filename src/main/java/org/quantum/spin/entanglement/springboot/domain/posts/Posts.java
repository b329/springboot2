package org.quantum.spin.entanglement.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /* Jpa 의 영속성 컨텍스트 떄문에 update 기능에서 데이터베이스 쿼이를 날리는 부분이 없다.
    *  영속성 컨텍스트 : 엔티티를 영구 저장하는 환경.
    *  Jpa 의 핵심내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.
    *  쿼리를 날릴 필요가 없는 이 부분을 Dirty Cheking 이라고도 함. */

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
