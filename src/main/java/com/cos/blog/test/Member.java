package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//lombok 

// Getter랑 Setter를 따로 만들 필요없이 이렇게 어노테이션을 사용하면 된다.
// 여기서 @Data는 Getter과 Setter를 모두 포함한 것이다.
// @Getter
// @Setter
@Data

// 생성자
@AllArgsConstructor // 인자가 있는 생성자 -> Member m = new Member(id,username,password,email)
@NoArgsConstructor // 인자가 없는 생성자 -> Member m = new Member()

// @RequiredArgsConstructor
// 이건 final에 대한 생성자를 만들어준다

// 또한,
// @builder
// public Member(int id, String username, String password, String email) {
// this.id = id;
// this.username = username;
// this.password = password;
// this.email = email;
// }
// 으로 builder를 해주면 생성자 오버로딩 할 필요없이 원하는 값만 넣을 수가 있다.
// ex) Member m = Member.builder().username("cos").password("12345").build();
// ----------------------------------------------------------------------------//

public class Member {

    private int id;
    private String username;
    private String password;
    private String email;

    // private final int id;
    // private final String username;
    // private final String password;
    // private final String email;
    // 해당 데이터들은 어차피 DB에서 가져오므로 불변성이 유지되어야한다. 그래서 Final이 붙는다.
    // 하지만 변경할 일이 있으면 final이 붙으면 안된다.

    // public Member(int id, String username, String password, String email) {
    // this.id = id;
    // this.username = username;
    // this.password = password;
    // this.email = email;
    // }

    // public int getId() {
    // return this.id;
    // }

    // public void setId(int id) {
    // this.id = id;
    // }

    // public String getUsername() {
    // return this.username;
    // }

    // public void setUsername(String username) {
    // this.username = username;
    // }

    // public String getPassword() {
    // return this.password;
    // }

    // public void setPassword(String password) {
    // this.password = password;
    // }

    // public String getEmail() {
    // return this.email;
    // }

    // public void setEmail(String email) {
    // this.email = email;
    // }

}
