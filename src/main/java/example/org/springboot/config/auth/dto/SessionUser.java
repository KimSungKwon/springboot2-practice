/*
    User 클래스 대신 쓰기. User 클래스 대신 직렬화 기능 있음
 */

package example.org.springboot.config.auth.dto;

import example.org.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
