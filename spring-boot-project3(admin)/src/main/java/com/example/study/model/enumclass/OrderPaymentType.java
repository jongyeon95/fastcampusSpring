package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderPaymentType {
    CASH(0,"현금","현금결제"),
    CARD(1,"카드","카드결제"),
    BANK_TRANSFER(2,"계좌 이체", "계좌 이체 결제"),
    CHECK_CARD(3,"체크카드","체크카드 결제");


    private Integer id;
    private String title;
    private String description;
}
