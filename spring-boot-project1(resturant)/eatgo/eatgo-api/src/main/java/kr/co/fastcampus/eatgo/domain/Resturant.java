package kr.co.fastcampus.eatgo.domain;

public class Resturant {
    private  final String name;
    private  final String address;
    private  final Long id;

    public  Resturant(long l, String name, String address){
        this. name=name;
        this. address=address;
        this. id=l;
    }



    public String getName() {
        return name;
    }

    public String getAddress(){
        return  address;
    }

    public String getInformation() {
        return  name + " in "+address;
    }

    public Long getId() {
        return  id;
    }
}
