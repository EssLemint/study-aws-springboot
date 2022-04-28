package com.study.studyjpa.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_BOARD")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "title",nullable = false, length = 20)
    private String title;

    @Lob
    @Column(name = "contents",nullable = false, length = 200)
    private String contents;

    @Column(name = "writer", length = 10)
    private String writer;

    @Column(name = "제작날짜")
    @CreationTimestamp
    private LocalDateTime createdDateTime;
    @Column(name = "수정날짜")
    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

    @Column(name = "사용여부")
    private boolean useFlag = true;
    @Column(name = "삭제여부")
    private boolean delFlag = false;

    private Board(String title, String contents, String writer){
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }

    public static Board createEntity(String title, String contents, String writer){
        return new Board(title, contents, writer);
    }

    public void updateBoard(String title, String contents, String writer){
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }
}
