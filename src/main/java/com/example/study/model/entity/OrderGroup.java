package com.example.study.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user","orderDetailList"})
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String orderType; // 개별이냐 일괄 배송이냐

    private String revAddress;

    private String revName;

    private String paymentType; // card or cash

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;


    //OrderGroup n : 1 User
    @ManyToOne
    private User user; // user 엔티티에 있는 mappedBy 의 명과 일치해야한다.

    //OrderGroup 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;

}
