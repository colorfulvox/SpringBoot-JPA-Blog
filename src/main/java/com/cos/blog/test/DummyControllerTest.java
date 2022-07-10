package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.print.attribute.standard.MediaSize.Other;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import net.bytebuddy.TypeCache.Sort;

@RestController
public class DummyControllerTest {

    @Autowired // DummyControllerTest가 실행될때 UserRepository이것도 스캔해달라는 것[그래야 해당 인테페이스를 사용할 수 있다.]
    private UserRepository userRepository;
    // 인터페이스가 DB관리JPA를 상속하고 있어 사용이 가능하다. [의존성 주입]
    // -----------------------------------------------------------------------------------------

    // 데이터 Get 요청
    @GetMapping("/dummy/user/{id}")
    // {id} 주소로 파라미터를 전달 받을 수 있다.
    // http://localhost:8000/blog/dummy/user/3 #3 -> {id}
    public User detail(@PathVariable int id) { // id로 해당 유저를 검색할때 있는지 없는지 판단을 해야한다.

        // Optional로 user객체를 감싸서 가져오기에 Null인지 아닌지 판단 해야된다.

        // User user = userRepository.findById(id).get();
        // DB에서 해당 id에대한 user를 가져오지만 null일 수도 있어 위험하다.

        // 해당 id값의 user가 null이면 Supplier인터페이스를 활용하여 새로운 default User를 리턴해버린다.
        // User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
        // @Override
        // public User get() {
        // return new User();
        // }
        // });
        // 하지만 이것도 선호하진 않는다.

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                // TODO Auto-generated method stub
                return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
            }
        });

        // 람다식으로도 작성 가능하다.
        // User user = userRepository.findById(id).orElseThrow(() -> {
        // return new IllegalArgumentException("해당 유저는 없는니다. id : " + id);
        // });

        // 요청 : 웹브라우저
        // user 객체는 자바 오브젝트
        // 근데 RestController는 데이터를 리턴해준다.
        // 그래서 user를 html이 이해할 수 있는 데이터로 변환 해서 리턴 해줘야한다.
        // Spring Boot는 MessageConvert가 응답시에 자동으로 작동하기에
        // 만약 자바 오브젝트를 리턴하게 되면 MessageConvert가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 Json으로 변환해서 html에 보내준다.
        return user;
    }

    // 리스트 Get요청
    @GetMapping("/dummy/user")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한페이지당 2건에 데이터를 id를 기준으로 최신순으로 리턴받아 온다.
    @GetMapping("/dummy/user/page")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable) {

        List<User> users = userRepository.findAll(pageable).getContent();
        return users;
    }
    // --------------------------------------------------------------------------------------------------

    // Post 요청
    @PostMapping("/dummy/join")
    public String join(User user) { // key = value (약속된 규칙)
        // System.out.println(user.getUsername());
        // System.out.println(user.getPassword());
        // System.out.println(user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원 가입 완료.";
    }
    // --------------------------------------------------------------------------------------------------

    // Put 요청
    @Transactional
    // userRepository.save를 하지 않아도 함수가 종료되면 알아서 commit해준다.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 유저는 없습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // userRepository.save(user); // 알아서 해당 id를 찾아서 정보를 업데이트 해준다.

        return user;
    }
    // --------------------------------------------------------------------------------------------------

    // delete 요청
    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable int id) {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
            return "해당 ID는 없습니다.";
        }

        return "삭제 완료";
    }

}
