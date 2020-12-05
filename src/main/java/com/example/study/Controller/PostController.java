package com.example.study.Controller;


import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    //HTML <Form>
    // ajax 검색
    // http post body -> data
    //json, xml, multipart-form / text-plain 형태 다 가능


  @PostMapping(value = "/postMethod")
    public String postMethod(@RequestBody SearchParam searchParam){
      // body 에 담긴 data를 받는다.

      return "OK";
  }
}
