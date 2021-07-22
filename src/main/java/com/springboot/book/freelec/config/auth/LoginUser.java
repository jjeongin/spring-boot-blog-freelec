package com.springboot.book.freelec.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // annotation 이 생성될 수 있는 위치 지정.
// method 의 PARAMETER 로 선언된 객체에서만 사용 가능.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // annotation class
}
