package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 스프링이 com.cos.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 New하는 것은 아니다.
// 특정 어노테이션이 붙어있는 클래스 파일들을 New해서(IoC) 스프링 컨테이너에 관리해줍니다.

public class BlogControllerTest {

    @GetMapping("/test/hello")
    public String Hello() {
        return "<h1>Hello Spring Boot</h1>";
    }

}
