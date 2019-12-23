package kr.co.fastcampus.eatgo.domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Resturant {
    private   String name;
    private   String address;
    private   Long id;
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

}
