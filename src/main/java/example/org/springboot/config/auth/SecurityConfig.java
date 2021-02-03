package example.org.springboot.config.auth;

import example.org.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정들을 활성화 시킴
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()    // URL 별 권한 관리를 설정하는 옵션의 시작점. 이게 선언되야 antMatcher 사용가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()  // 권환 관리 대상 지정 옵션
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())    // USER 권한을 가진 사람만 가능
                    .anyRequest().authenticated()   // anyRequest: 설정된 값 이외 나머지 URL들. authenticated: 인증된(로그인) 사용자들에게만 허용
                .and()
                    .logout()       // 로그아웃 설정 진입점
                        .logoutSuccessUrl("/")  // 로그아웃 성공시 "/" 로
                .and()
                    .oauth2Login()  // OAuth2 로그인 설정 진입점
                        .userInfoEndpoint() // 로그인 성공 이후 사용자 정보를 가져올 때의 설정들 담당
                            .userService(customOAuth2UserService);  // 소셜 로그인 성공 시 후속 조치를 진행할 userService 구현체 등록
    }
}
