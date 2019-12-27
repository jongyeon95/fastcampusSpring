package kr.co.fastcampus.eatgo.domain;

public class ResturantNotFoundException extends RuntimeException{

    public ResturantNotFoundException(long id) {
        super("Could not find resturant" + id);
    }
}
