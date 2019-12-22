package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.Resturant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResturantController {
    @GetMapping("/resturants")
    public List<Resturant> list(){
        List<Resturant> resturants =new ArrayList<>();

        Resturant resturant = new Resturant(1004L,"Bob zip", "Seoul");
        resturants.add(resturant);
        return resturants;
    }
}
