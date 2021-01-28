/*
    Posts 클래스로 DB를 접근하게 해줌
 */
package example.org.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
