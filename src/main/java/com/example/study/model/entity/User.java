package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@Entity // =table
@Data
public class User {
    //jpa entity 는 camel case -> DB의 snake_case로 바꾼다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //어떤식으로 관리할 것인지.
    private  Long id;

    private  String account;
    private  String email;
    private  String phoneNumber;   //sql의 변수명과 달라도 jpa 에서 자동으로 변환해준다.
    private LocalDateTime createdAt;
    private String createdBy;

    private  LocalDateTime updatedAt;

    private String updatedBy;


    public User() {

    }
}
