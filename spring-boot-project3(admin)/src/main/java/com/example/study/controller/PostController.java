package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    @PostMapping(value="/postMethod")
    public SearchParam PostMethod(@RequestBody SearchParam searchParam){
        searchParam.setPage(searchParam.getPage()+1);
        return searchParam;
    }

    public void put(){}
    public void patch(){}
}
