package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {
    @RequestMapping(method = RequestMethod.GET,path = "/getMethod")
    public String GetRequest(){
        return "Hi getMethod";
    }

    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam String password){
        System.out.println("id: "+id);
        System.out.println("password: "+password);
        return id+password;
    }
    @GetMapping("/getMultiParameter")
    public String getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());
        return "완료";
    }
}
