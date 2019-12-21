package kr.co.fastcampus.eatgo.domain;

public class Resturant {
    private  final String name;
    private  final String address;

    public  Resturant(String name){
        this. name=name;
        this. address="";
    }

    public Resturant(String name, String address) {
        this . name =name;
        this.address=address;
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
}
