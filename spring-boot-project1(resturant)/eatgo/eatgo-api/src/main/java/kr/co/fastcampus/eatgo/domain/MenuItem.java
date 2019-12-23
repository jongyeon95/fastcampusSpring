package kr.co.fastcampus.eatgo.domain;

public class MenuItem {
    private  final String name;
    public MenuItem(String Mname){
        this.name=Mname;
    }
    public String getName(){
        return name;
    }
}
