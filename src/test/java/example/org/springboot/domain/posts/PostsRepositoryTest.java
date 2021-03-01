package example.org.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach  // Junit에서 단위 테스트가 끝날 때 마다 수행되는 메소드 (cleanup) 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void saveAndLoadPosts() {
        // given
        String title = "Test Title";
        String content = "Test content";

        postsRepository.save(Posts.builder()    // 테이블 posts에 insert/update 쿼리 실행. id값이 있으면 insert. 없으면 update
                .title(title)
                .content(content)
                .author("sungkwonnn@gmail.com").build());

        // when
        List<Posts> postsList = postsRepository.findAll();  // findAll: 테이블 posts에 있는 모든 데이터를 조회해오는 메소드.

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);  // assertThat : 검증하고 싶은 대상을 메소드 인자로 받음
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
