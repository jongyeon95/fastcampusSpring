package com.example.study.model.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    COMPLETE(0,"완료","주문 처리 완료"),
    WAITING(1,"대기","주문 완료 대기중");

    private Integer id;
    private String title;
    private String description;
}
