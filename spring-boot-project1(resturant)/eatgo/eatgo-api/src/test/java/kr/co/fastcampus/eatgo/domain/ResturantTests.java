package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ResturantTests {
    @Test
    public  void creation(){
        Resturant resturant=Resturant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();
        assertThat(resturant.getName(),is("Bob zip"));
        assertThat(resturant.getId(),is(1004L));
        assertThat(resturant.getAddress(),is("Seoul"));
    }

    @Test
    public void information(){
        Resturant resturant=Resturant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();
    assertThat(resturant.getInformation(),is("Bob zip in Seoul"));
    }

}