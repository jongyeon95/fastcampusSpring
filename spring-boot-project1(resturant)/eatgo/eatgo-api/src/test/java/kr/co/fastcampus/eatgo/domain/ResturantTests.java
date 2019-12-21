package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class ResturantTests {
    @Test
    public  void creation(){
        Resturant resturant=new Resturant("Bob zip");
        assertThat(resturant.getName(),is("Bob zip"));
    }

    @Test
    public void information(){
    Resturant resturant =new Resturant("Bob zip", "Seoul");
    assertThat(resturant.getInformation(),is("Bob zip in Seoul"));
    }

}