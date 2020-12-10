package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    // select * from user where account =?
    Optional<User>findByAccount(String account);

    Optional<User>findByEmail(String email);

    // JPA 쿼리메소드
    Optional<User>findByAccountAndEmail(String account, String email);
}
