package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @Controller
// 사용자가 요청 -> 응답(HTML 파일) HTML같은 파일들을 응답해주는 어노테이션

// 사용자가 요청시 응답(Data)해주며 데이터를 응답해주는 어노테이션
@RestController
public class HttpControllerTest {

    // lombok Test
    private static final String TAG = "HttpControllerTest : ";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = new Member(1, "cos", "12345", "cost@nate.com");
        System.out.println(TAG + "getter : " + m.getId());
        m.setId(5000);
        System.out.println(TAG + "setter : " + m.getId());

        return "lombok test 완료";
    }

    // 인터넷 브라우저 요청에는 get밖에 안된다.

    // http://localhost:8080/http/get
    // (select)-----------------------------------------------------------------------

    // @RequestParam int id는 요청한 데이터를 응답해준다.
    // public String getTest(@RequestParam int id) {
    // return "get 요청 : " + id;
    // }

    // Member m
    // 하지만 클래스로 보내면 알아서 맵핑해서 응답해준다. (MessageConverter)
    @GetMapping("/http/get") // get?id=1&username=ssar&password=1234&email=ssar@nate.com
    public String getTest(Member m) {

        return "get 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    // http://localhost:8080/http/post
    // (insert)-----------------------------------------------------------------------
    @PostMapping("/http/post")

    // [POSTMAN] -> Body에서 요청을 보낸다.
    // x-www-from-urlencoded방식은 HTTP에서 Input방식이랑 똑같다.
    // x-www-from-urlencoded방식은 이 메서드 방식을 사용하면 된다.

    // public String postTest(Member m) {
    // return "post 요청 " + m.getId() + "," + m.getUsername() + "," + m.getPassword()
    // + "," + m.getEmail();
    // }

    // raw에서 Text방식은 Body에서 요청이오므로 @RequestBody어노테이션을 붙이고 작성한다.
    // public String postTest(@RequestBody String text) { // text/plain
    // return "post 요청 : " + text;
    // }

    // raw에서 Json방식은
    // {
    // "id" : 1,
    // "username" : "ssar",
    // "password" : 123456,
    // "email" : "cos@nate.com"
    // }
    // 이렇게 보내지므로 클래스를 인자로 요청하면
    // SpringBoot에서 알아서 맵핑해서 보내진다. (MessageConverter)
    public String postTest(@RequestBody Member m) { // application/Json
        return "post 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    // http://localhost:8080/http/put
    // (update)-----------------------------------------------------------------------
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }

}
