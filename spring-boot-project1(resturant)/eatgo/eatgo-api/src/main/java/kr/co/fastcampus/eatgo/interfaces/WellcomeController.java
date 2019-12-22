package kr.co.fastcampus.eatgo.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WellcomeController {
    @GetMapping("/")
    public String Hello(){
        return  "Hello World";
    }
}
