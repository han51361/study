package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // =table
@ToString(exclude = {"user"})
public class User {
    //jpa entity 는 camel case -> DB의 snake_case로 바꾼다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //어떤식으로 관리할 것인지.
    private  Long id;

    private  String account;
    private String password;

    private String status;

    private  String email;
    private  String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;
    private String createdBy;

    private  LocalDateTime updatedAt;

    private String updatedBy;


    // User 1 : n OrderGroup
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<OrderGroup> orderGroupList;


}
