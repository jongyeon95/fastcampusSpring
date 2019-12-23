package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class ResturantServiceTest {
    private ResturantService resturantService;
    @Mock
    private ResturantRepository resturantRepository;
    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public  void setUp(){
        MockitoAnnotations.initMocks(this);//Mock초기화
        mockResturantRepository();
        mockMenuItemRepository();
        resturantService=new ResturantService(resturantRepository, menuItemRepository);
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems=new ArrayList<>();
        MenuItem menuItem=new MenuItem("Kimchi");
        menuItems.add(menuItem);
        given(menuItemRepository.findAllByResturantId(1004L)).willReturn(menuItems);
    }

    private void mockResturantRepository() {
        List<Resturant> resturants =new ArrayList<>();
        Resturant resturant = new Resturant(1004L, "Bob wip","Seoul");
        resturants.add(resturant);
        given(resturantRepository.findAll()).willReturn(resturants);
        given(resturantRepository.findById(1004L)).willReturn(resturant);
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

    @Test
    public void addResturant(){
        Resturant resturant= new Resturant("BeRyong","Busan");
        Resturant saved= new Resturant(1234L,"BeRyong","Busan");
        given(resturantRepository.save(any())).willReturn(saved);
        Resturant created = resturantService.addResturant(resturant);
        assertThat(created.getId(),is(1234L));
    }

}