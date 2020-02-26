package com.example.study.model.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderPaymentType {
    CASH(0,"현금","현금결제"),
    CARD(1,"카드","카드결제");

    private Integer id;
    private String title;
    private String description;
}
