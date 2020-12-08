package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;



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

    @Test
    public void read(){
       Optional< User> user = userRepository.findById(2L); //id type 이 long이라

      user.ifPresent(selectUser->{
           System.out.println("user: "+selectUser);
       });
    }

    @Test
    public  void update(){
        Optional< User> user = userRepository.findById(2L); //id type 이 long이라

        user.ifPresent(selectUser->{
            selectUser.setAccount("ppppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });

    }
    //@DeleteMapping("/api/user")
    @Test
    @Transactional
    public void delete(){
        Optional<User>user = userRepository.findById(1L);

        Assertions.assertTrue(user.isPresent()); //true
        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User>deleteUser = userRepository.findById(1L);
      Assertions.assertFalse(deleteUser.isPresent()); //false


    }


}
