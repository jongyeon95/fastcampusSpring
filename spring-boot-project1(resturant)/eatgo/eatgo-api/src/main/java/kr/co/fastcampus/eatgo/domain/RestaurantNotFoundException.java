package kr.co.fastcampus.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException(long id) {
        super("Could not find restaurant" + id);
    }
}
