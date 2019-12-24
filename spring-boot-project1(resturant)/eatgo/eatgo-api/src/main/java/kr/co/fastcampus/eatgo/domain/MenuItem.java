package kr.co.fastcampus.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue
    private Long id;

    private Long resturantId;
    private  final String name;

    public MenuItem(String Mname){
        this.name=Mname;
    }
    public String getName(){
        return name;
    }
}
