package kr.co.fastcampus.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Resturant {

    @Id
    @GeneratedValue
    private   Long id;
    private   String name;
    private   String address;

    @Transient
    private List<MenuItem> menuItems=new ArrayList<>();


    public  Resturant(){}
    public Resturant(String name, String address) {
        this. name=name;
    }
    public  Resturant(long l, String name, String address){
        this. name=name;
        this. address=address;
        this. id=l;
    }


    public void setId(long id) {
        this.id=id;
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

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

    public void addMenuItem(MenuItem menuitem) {
        menuItems.add(menuitem);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems){
            addMenuItem(menuItem);
        }
    }



    public void updateInformation(String name, String address) {
        this.name=name;
        this.address=address;
    }
}
