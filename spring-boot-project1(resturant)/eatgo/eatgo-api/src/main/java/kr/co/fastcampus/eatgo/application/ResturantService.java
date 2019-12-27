package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class ResturantService {

    @Autowired
    ResturantRepository resturantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    public ResturantService(ResturantRepository resturantRepository, MenuItemRepository menuItemRepository) {
        this.resturantRepository =resturantRepository;
        this.menuItemRepository=menuItemRepository;
    }

    public Resturant getResturant(Long id){
        Resturant resturant=resturantRepository.findById(id).orElseThrow(()->new ResturantNotFoundException(id));

        List<MenuItem> menuItems=menuItemRepository.findAllByResturantId(id);
        resturant.setMenuItems(menuItems);
        return resturant;
    }


    public List<Resturant> getResturants() {
        List<Resturant> resturants= resturantRepository.findAll();
        return resturants;
    }

    public Resturant addResturant(Resturant resturant) {
        Resturant saved=resturantRepository.save(resturant);
        return saved;
    }

    @Transactional
    public Resturant updateResturant(Long id, String name, String address) {
        Resturant resturant = resturantRepository.findById(id).orElse(null);
        resturant.updateInformation(name,address);

        return resturant;
    }
}
