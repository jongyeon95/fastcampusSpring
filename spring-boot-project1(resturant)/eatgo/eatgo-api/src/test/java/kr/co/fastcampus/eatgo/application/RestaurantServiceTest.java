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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {
    private RestaurantService restaurantService;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private MenuItemRepository menuItemRepository;
    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public  void setUp(){
        MockitoAnnotations.initMocks(this);//Mock초기화
        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();
        restaurantService =new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);
    }

    private void mockReviewRepository() {
        List<Review> reviews=new ArrayList<>();
        reviews.add(Review.builder()
        .id(1004L)
        .name("JOKER")
        .score(3)
        .description("great")
        .build());
        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems=new ArrayList<>();
        menuItems.add(MenuItem.builder()
        .name("Kimchi")
        .build());
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants =new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();
        restaurants.add(restaurant);
        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(java.util.Optional.of(restaurant));
    }

    @Test
    public void getRestaurants(){
      List<Restaurant> restaurants = restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(),is(1004L));
    }

    @Test
    public void getRestaurantWithExisted(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertThat(restaurant.getId(),is(1004L));
        MenuItem menuItem= restaurant.getMenuItems().get(0);

        assertThat(menuItem.getName(),is("Kimchi"));

        Review review=restaurant.getReviews().get(0);

        assertThat(review.getDescription(),is("great"));

    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWitNotExisted(){
       restaurantService.getRestaurant(404L);
    }

    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation ->{
            Restaurant restaurant =invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });
        Restaurant restaurant = Restaurant.builder()
                .address("Busan")
                .name("BeRyong")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);
        assertThat(created.getId(),is(1234L));
    }

    @Test
    public  void updateRestaurant(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();
        given(restaurantRepository.findById(1004L)).willReturn(java.util.Optional.of(restaurant));
        restaurantService.updateRestaurant(1004L,"Sool zip","Busan");
        assertThat(restaurant.getName(), is("Sool zip"));
        assertThat(restaurant.getAddress(),is("Busan"));
    }


}