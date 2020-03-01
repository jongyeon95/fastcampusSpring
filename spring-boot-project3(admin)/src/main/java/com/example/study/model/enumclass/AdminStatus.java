package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminStatus {
    REGISTERED(0,"등록","관리자 등록상태"),
    UNREGISTERED(1,"해지","관리자 해지상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
