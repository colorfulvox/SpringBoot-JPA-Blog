package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴

@Entity // 클래스 테이블화 어노테이션 [MySQL에 자동으로 테이블이 생성된다.]

// @DynamicInsert
// DB에 Insert할때 null인값은 제외하고 저장해준다. 예를들어, role같은 경우는 Default가 user인데 role값을 생략하고
// insert하면 null이 되버린다. 그래서 DynamicInsert로 null값은 제외 시켜준다.
// 하지만 어노테이션이 이렇게 늘어나면 복잡해진다.
// 그래서 저장할때 role은 따로 Enum을 만들어 관리 해준뒤 DB를 실제로 저장할때 따로 저장해준다.

public class User { // 유저 테이블

    @Id // DB에서 Id인것을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다고 명시. Mysql -> auto_increment
    private int id;

    @Column(nullable = false, length = 30) // 유저네임은 null이 될수 없고 30자 이상이 될 수 없다는 것을 명시
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 비밀번호가 100개인 이유는 해쉬를 통해서 암호화 하려고 한다.
    private String password; // 비밀번호

    @Column(nullable = false, length = 50)
    private String email; // 이메일

    // @ColumnDefault(" 'user' ") // Default로 user를 준다.
    // private String role;
    // Enum을 쓰는게 좋다. [admin인지 user인지 manager인지 확인 하여 권한을 주기 위한것] 그래서
    // admin,user,manager에 대한 도메인 설정할 수 있는 Enum을 쓰는게 좋다.

    @Enumerated(EnumType.STRING) // DB는 RoleType(Enum)이 없기에 명시해준다.
    private RoleType role;

    @CreationTimestamp // 시간이 자동으로 DB에 등록이 된다.
    private Timestamp createDate; // 시간 기록

}
