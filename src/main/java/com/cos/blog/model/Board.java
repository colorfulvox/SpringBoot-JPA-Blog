package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Board { // 게시글 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title; // 제목

    @Lob // 대용량 데이터를 사용할때 쓴다.
    private String content; // 섬머노트라는 라이브러를 사용하여 <html>태그가 섞여서 디자인이 된다.

    @ColumnDefault("0")
    private int count; // 조회수

    // private int userId
    // 해당 게시글의 작성자의 아이디를 가져와야한다.
    // 하지만 ORM에서는 User 클래스의 Id를 가져와 Board테이블에 FK로 삽입해줄 수 있다.

    @ManyToOne(fetch = FetchType.EAGER) // Many -> Board , One -> user [한명의 유저는 여러개의 게시글을 쓸 수 있다.]
    // FK(id)와 Board테이블에 대한 DB의 연관 관계
    @JoinColumn(name = "userId") // 칼럼의 이름을 userId로 하겠다.
    private User user; // 게시글의 작성자 Id

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    // mappedBy는 연관관계의 주인이 아니라는 것을 명시 (reply는 FK가 아니니까 칼럼을 만들지 말라는 요청)\
    // board는 Reply에 있는 Board클래스의 FK 필드 이름
    // @JoinColumn(name = "replyId")
    // FK키가 필요없다. 하나의 게시글에는 여러 답변글이 와야 된다. (1정규화 원자성 위배)
    private List<Reply> reply;
    // ORM은 Join을 통해서 User,Reply의 정보를 모두 가져오게된다.
    // 답변은 하나의 게시글에 여러개의 답변이 올 수 있기에 List로 관리한다.

    @CreationTimestamp
    private Timestamp createDate;

}
