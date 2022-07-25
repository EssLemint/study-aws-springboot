package com.lemint.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Target : 어노테이션이 생성 될 수 있는 위치를 지정한다
 * PARAMETER :  메소드의 파라미터로 선언된 객체에서만 사용 할 수 있다. (클래스  선언문에 쓸 수 있는 TYPE 존재)
 * */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 해당 파일을 어노테이션 클래스로 지정
 * -> LoginUser 이름의 어노테이션 생성
 * */
public @interface LoginUser {
}
