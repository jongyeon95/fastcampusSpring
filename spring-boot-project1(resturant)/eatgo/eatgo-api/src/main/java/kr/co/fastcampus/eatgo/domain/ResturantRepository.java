package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ResturantRepository extends CrudRepository<Resturant, Long> {
    List<Resturant> findAll();

    Optional<Resturant> findById(Long id);

    Resturant save(Resturant resturant);
}
