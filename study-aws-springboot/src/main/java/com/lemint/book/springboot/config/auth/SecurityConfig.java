package com.lemint.book.springboot.config.auth;

import com.lemint.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화시켜 준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomOAuth2UserService customOAuth2UserService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        /**
         * csrf().disable().headers().frameOptions().disable() :
         *  h2-console 화면 사용을 위해 해당 옵션들을 disable
         * */
        .csrf().disable()
        .headers().frameOptions().disable()
        .and()
        /**
         * authorizeRequests() :
         * URL별 권한 관리를 설정하는 옵션의 시작점, authorizeRequests가 선언 되어야만 antMachers옵션을 사용 할 수 있다
         * */
        .authorizeRequests()
        /**
         * antMatchers() :
         * 권한 관리 대상을 지정하는 옵션, URL과 HTTP 메소드별로 관리가 가능하다.
         * "/" 등 지정된 URL들은 permitAll() 옵션으로 전체 권한을 줌
         * "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하게
         * */
        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
        /**
         * anyRequest() :
         * 설정된 값들 이외 나머지 URL들을 나타낸다.
         *
         * authenticated() :
         * 추가하여 나머지 URL들은 모두 인증된 사용자(로그인한 사용자)들에게만 허용한다.
         * */
        .anyRequest().authenticated()
        .and()
        /**
         *  logout().logoutSuccessUrl("/") :
         *  로그아웃 기능에 대해서 여러 설정의 진입점
         *  로그아웃 성공시 "/" 주소로 이동
         * */
        .logout()
        .logoutSuccessUrl("/")
        .and()
        /**
         * oauth2Login() :
         * OAuth2 로그인 기능에 대한 설정의 진입점.
         * */
        .oauth2Login()
        /**
         * userInfoEndpoint() :
         * OAuth2 로그인 성공 이후 사용자 정보를 가져올 설정 담당
         * */
        .userInfoEndpoint()
        /**
         * userService
         * 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다.
         * 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시 할 수 있다.
         * */
        .userService(customOAuth2UserService);
  }
}
