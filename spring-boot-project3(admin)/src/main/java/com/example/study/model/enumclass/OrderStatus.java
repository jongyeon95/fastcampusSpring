package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    COMPLETE(0,"완료","주문 처리 완료"),
    ORDERING(1,"주문중","주문중"),
    CONFIRM(2,"확인","주민 확인 완료");

    private Integer id;
    private String title;
    private String description;
}
