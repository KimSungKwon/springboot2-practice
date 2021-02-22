package example.org.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing  // JPA Auditing 활성화   config/JpaConfig 에 있음
@SpringBootApplication  // 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); // run WAS
    }
}
