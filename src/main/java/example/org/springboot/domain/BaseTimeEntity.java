/*
    모든 Entity의 상위 클래스가 되어 Entity들의 createDate, modifiedDate를 자동으로 관리함.
    JPA Auditing: 시간에 대해 자동으로 값을 넣어주는 기능
 */

package example.org.springboot.domain;

import lombok.Getter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // Entity 들이 BaseTimeEntity 를 상속할 경우 필드들(c.D, m.D)도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class)  // BaseTimeEntity 클래스에 Auditing 기능을 포함시킴
public abstract class BaseTimeEntity {

    @CreatedDate        // Entity가 생성되어 저장될 때 시간이 자동 저장됨
    private LocalDateTime createdDate;

    @LastModifiedDate   // 조회한 Entity의 값을 변경할 때 시간이 자동 저장됨
    private LocalDateTime modifiedDate;
}
