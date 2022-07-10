package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 Expection이 발생하면 해당 클래스가 호출 된다.
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class) // IllegalArgumentException 에러에 대한 처리 메서드
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }

    // 다른 Exception을 처리할때도 똑같이 처리하면 된다. 근데 보통 Exception만 해줘도 다 반응 한다.
    // @ExceptionHandler(value = Exception.class)
}
