package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // =table
public class User {
    //jpa entity 는 camel case -> DB의 snake_case로 바꾼다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //어떤식으로 관리할 것인지.
    private  Long id;

    private  String account;
    private  String email;
    private  String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;

    private  LocalDateTime updatedAt;

    private String updatedBy;


    //1 : N fetch 타입 걸어두기
    // 1: N 관계여서 orderDeetail 은 List 형태로 받아온다.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderDetail> orderDetailList;


}
