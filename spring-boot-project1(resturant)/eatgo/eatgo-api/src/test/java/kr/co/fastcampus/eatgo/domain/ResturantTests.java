package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ResturantTests {
    @Test
    public  void creation(){
        Resturant resturant=new Resturant(1004L,"Bob zip","Seoul");
        assertThat(resturant.getName(),is("Bob zip"));
        assertThat(resturant.getId(),is(1004L));
        assertThat(resturant.getAddress(),is("Seoul"));
    }

    @Test
    public void information(){
    Resturant resturant =new Resturant(1004L,"Bob zip", "Seoul");
    assertThat(resturant.getInformation(),is("Bob zip in Seoul"));
    }

}