package kr.co.fastcampus.eatgo.domain;

import java.util.List;

public interface ResturantRepository {
    List<Resturant> findAll();

    Resturant findById(Long id);
}
