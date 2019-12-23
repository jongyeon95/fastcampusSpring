package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ResturantServiceTest {
    private ResturantService resturantService;
    private ResturantRepository resturantRepository;
    private MenuItemRepository menuItemRepository;

    @Before
    public  void setUp(){
        resturantRepository =new ResturantRepositoryImpl();
        menuItemRepository = new MenuItemRepositoryImpl();
        resturantService=new ResturantService(resturantRepository, menuItemRepository);


    }
    @Test
    public void getResturants(){
      List<Resturant> resturants = resturantService.getResturants();
        Resturant resturant=resturants.get(0);
        assertThat(resturant.getId(),is(1004L));
    }

    @Test
    public void getResturant(){
        Resturant resturant = resturantService.getResturant(1004L);
        assertThat(resturant.getId(),is(1004L));
        MenuItem menuItem= resturant.getMenuItems().get(0);
        assertThat(menuItem.getName(),is("Kimchi"));
    }

}