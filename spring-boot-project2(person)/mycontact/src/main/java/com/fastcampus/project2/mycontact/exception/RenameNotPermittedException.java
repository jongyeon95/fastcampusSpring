package com.fastcampus.project2.mycontact.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RenameNotPermittedException extends RuntimeException{
    private static final String MESSAGE="이름 변경이 혀용되지 않습니다.";
    public RenameNotPermittedException(){
        super(MESSAGE);
        log.error(MESSAGE);
    }
}
