package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//DB의 데이터를 실제로 관리하는 인터페이스
public interface UserRepository extends JpaRepository<User, Integer> {

}
// SAVE
// save함수는 id를 전달하지 않으면 insert를 해주고
// id가 있으면 업데이트를 해주고
// id를 전달했는데 해당 id가 없으면 insert를 해버린다.