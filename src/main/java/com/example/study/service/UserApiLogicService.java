package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {


    @Autowired
    private UserRepository userRepository;



    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        //1. request data
        UserApiRequest userApiRequest = request.getData();


        // 2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);


        //3. 생성된 데이터 -> UserApiResponse return




        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        //id ->  repository getOne, getById
      return userRepository.findById(id)
                .map(user ->response(user))
                .orElseGet( //없다면
                        ()->Header.ERROR("NO DATA"));

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data
        UserApiRequest userApiRequest = request.getData();


        //2. id -> user

       Optional<User> optional = userRepository.findById(userApiRequest.getId());

       return optional.map(user -> {
           //3.  ㅇㅁㅅㅁ  -> update
           // 해당 아이디에서 대해 update
           user.setAccount(userApiRequest.getAccount())
                   .setPassword(userApiRequest.getPassword())
                   .setStatus(userApiRequest.getStatus())
                   .setPhoneNumber(userApiRequest.getPhoneNumber())
                   .setEmail(userApiRequest.getEmail())
                   .setRegisteredAt(userApiRequest.getRegisteredAt())
                   .setUnregisteredAt(userApiRequest.getUnregisteredAt());

           return user;
       })
               .map(user -> userRepository.save(user)) //update
               .map(updateUser->response(updateUser)) //userApiResponse 생성
               .orElseGet(() ->Header.ERROR("NO data"));



        //4. userApriResponse

    }

    @Override
    public Header delete(Long id) {

        // id -> repository -> user
           Optional<User> optional = userRepository.findById(id);


        // repository -> delete
        return optional.map(user -> {
            userRepository.delete(user);

            return Header.OK();
        }).orElseGet(()-> Header.ERROR("No data"));

        //response return


    }

    private Header<UserApiResponse> response(User user){
        // user -> userapiresponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())  //todo 암호화 , 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data
        return Header.OK(userApiResponse);
    }
}
