package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private  Integer price;

   private String content;


   //1 : N
    //Fetch Type
    // Lazy = 지연로딩 Eager = 즉시로딩
    // lazy = select * from item where id =?
    // Eager =  Join Item.item id = detail.itemid Join User.user_id = detail.user_id
    // 따라서  Eager = 1:1
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
