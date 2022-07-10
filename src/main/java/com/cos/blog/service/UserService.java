package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// Service가 필요한 이유
// 트랜잭션 관리 
// 서비스 의미를 알아야 된다. -> 여러개의 기능(트랜잭션)들이 묶여있는 형태가 서비스이다. 송금만 해도 update과정이 여러번이 일어나기에
// 당연히 서비스가 실패하면 롤백도 해주는 기능이 있어야한다.

@Service // SpringBoot가 컴포넌트 스캔을 통해서 Bean에 등록을 해줌(IoC를 해준다.)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int 회원가입(User user) {

        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("회원가입 " + e.getMessage());
        }
        return -1;
    }
}
