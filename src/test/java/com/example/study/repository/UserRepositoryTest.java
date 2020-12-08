package com.example.study.repository;

import com.example.study.StudyApplication;
import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserRepositoryTest extends StudyApplicationTests {


    @Autowired  //spirng의 장점 : DI(Dependency Injection) 의존성들을 주입
    private UserRepository userRepository;  //자동적으로 autowired된 놈을 주입

    @Test
    public void create(){
        User user = new User(); //Singleton

        user.setAccount("TestUser01");
        user.setEmail("TestUser@gmail.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");


       User newUser = userRepository.save(user);
        System.out.println("newUser : "+newUser);
    }

    public void read(){

    }

    public  void update(){

    }

    public void delete(){


    }


}
