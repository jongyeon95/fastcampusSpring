package kr.co.fastcampus.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ResturantRepositoryImplTest {
    @Test
    public void save(){
        ResturantRepository repository=new ResturantRepositoryImpl();
        int oldCount=repository.findAll().size();
        Resturant resturant=new Resturant("BeRyong","Seoul");
        repository.save(resturant);
        assertThat(resturant.getId(),is(1234L));

        int newCount=repository.findAll().size();
        assertThat(newCount-oldCount,is(1));

    }
}