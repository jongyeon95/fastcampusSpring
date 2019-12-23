package kr.co.fastcampus.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ResturantRepositoryImpl implements ResturantRepository {
    private List<Resturant> resturants =new ArrayList<>();

    public ResturantRepositoryImpl(){
        resturants.add( new Resturant(1004L,"Bob zip", "Seoul"));
        resturants.add(new Resturant(2020L,"Cyber Food", "Seoul"));

    }
    @Override
    public List<Resturant> findAll() {

        return resturants;
    }

    @Override
    public Resturant findById(Long id) {
        Resturant resturant=resturants.stream()
                .filter(r->r.getId().equals(id))
                .findFirst()
                .orElse(null);
    return resturant;
    }

    @Override
    public Resturant save(Resturant resturant) {
        resturant.setId(1234);
        resturants.add(resturant);

        return resturant;
    }

}
