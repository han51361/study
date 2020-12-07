package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchParam {
    private  String account;
    private  String email;
    private int page;


}
//lombok 을통하여 아규먼트 설정하기 귀찮은 점을 줄일 수 잇다.