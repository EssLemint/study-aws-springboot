package com.lemint.book.springboot.config;

import com.lemint.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/***
 *  Spring에서  LoginUserArgumentResolver이 인식 되도록 하는 설정 파일
 */
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
  private final LoginUserArgumentResolver loginUserArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    /**
     * List<HandlerMethodArgumentResolver> argumentResolvers)
     *
     * HandlerMethodArgumentResolver은 WebMvcConfigurer을 통해서 추가 되어야한다.
     * */
    argumentResolvers.add(loginUserArgumentResolver);
  }
}
