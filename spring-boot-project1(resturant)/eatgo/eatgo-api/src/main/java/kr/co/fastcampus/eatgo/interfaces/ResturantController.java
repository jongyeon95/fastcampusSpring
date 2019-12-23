package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.ResturantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.eatgo.domain.Resturant;
import kr.co.fastcampus.eatgo.domain.ResturantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResturantController {

    @Autowired
    private ResturantService resturantService;

    @GetMapping("/resturants")
    public List<Resturant> list(){

        List<Resturant> resturants= resturantService.getResturants();
        return resturants;
    }

    @GetMapping("/resturants/{id}")
    public Resturant detail(@PathVariable("id") Long id){
        Resturant resturant=resturantService.getResturant(id);
        return resturant;

    }
}