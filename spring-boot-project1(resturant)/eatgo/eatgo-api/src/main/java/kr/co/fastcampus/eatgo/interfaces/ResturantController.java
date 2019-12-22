package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.Resturant;
import kr.co.fastcampus.eatgo.domain.ResturantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResturantController {
    private ResturantRepository repository=new ResturantRepository();
    @GetMapping("/resturants")
    public List<Resturant> list(){

        List<Resturant> resturants=repository.findAll();
        return resturants;
    }

    @GetMapping("/resturants/{id}")
    public Resturant detail(@PathVariable("id") Long id){
        Resturant resturant =repository.findById(id);
        return resturant;

    }
}