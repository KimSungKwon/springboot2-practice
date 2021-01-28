/*
    This is Entity
 */

package example.org.springboot.domain.posts;

import example.org.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Getter
@NoArgsConstructor  // 디폴트 생성자 자동 추가
@Entity // 테이블과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity {    // Entity 클래스. DB 데이터에 작업할 경우 이 클래스의 수정을 통해 작업
    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타냄
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타냄.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    // 해당 클래스의 빌더(생성자) 패턴 클래스를 생성 . 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author){  // Posts.builder().title(title).~~.build()
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
