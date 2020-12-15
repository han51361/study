package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
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

       String account = "Test01";
       String password = "Test01";
       String status = "Registered";
       String email = "Test01@gmail.com";
       String phoneNumber = "010-1111-2222";
       LocalDateTime registeredAt = LocalDateTime.now();
       LocalDateTime createdAt = LocalDateTime.now();
       String createdBy = "AdminServer";

    user.setAccount(account);
    user.setCreatedBy(createdBy);
    user.setPhoneNumber(phoneNumber);
    user.setPassword(password);
    user.setStatus(status);
    user.setEmail(email);
    user.setRegisteredAt(registeredAt);
    user.setCreatedAt(createdAt);



       User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("----------------장바구니------------------- ");
            System.out.println("수령인"+orderGroup.getRevName());
            System.out.println("총 금액"+orderGroup.getTotalPrice());
            System.out.println("배송지"+orderGroup.getRevAddress());
            System.out.println("총 수량"+orderGroup.getTotalQuantity());
            System.out.println("----------------주문 상세------------------- ");

            orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                System.out.println("주문 상품 : "+ orderDetail.getItem().getName());
                System.out.println("고객 센터 번호 : "+orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문 상태 : "+orderDetail.getStatus());
                System.out.println("도착 예정 : "+orderDetail.getArrivalDate());
                System.out.println("Partner name : "+orderDetail.getItem().getPartner().getName());
                System.out.println("partner's category :"+orderDetail.getItem().getPartner().getCategory().getTitle());

            });
        });

        Assertions.assertNotNull(user);

    }



}
