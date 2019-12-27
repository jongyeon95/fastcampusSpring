package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class RestaurantTests {
    @Test
    public  void creation(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();
        assertThat(restaurant.getName(),is("Bob zip"));
        assertThat(restaurant.getId(),is(1004L));
        assertThat(restaurant.getAddress(),is("Seoul"));
    }

    @Test
    public void information(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();
    assertThat(restaurant.getInformation(),is("Bob zip in Seoul"));
    }

}