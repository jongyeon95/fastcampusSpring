package kr.co.fastcampus.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class ResturantRepository {
    private List<Resturant> resturants =new ArrayList<>();

    public  ResturantRepository(){
        resturants.add( new Resturant(1004L,"Bob zip", "Seoul"));
        resturants.add(new Resturant(2020L,"Cyber Food", "Seoul"));

    }
    public List<Resturant> findAll() {

        return resturants;
    }

    public Resturant findById(Long id) {
        Resturant resturant=resturants.stream()
                .filter(r->r.getId().equals(id))
                .findFirst()
                .orElse(null);
    return resturant;
    }

}
