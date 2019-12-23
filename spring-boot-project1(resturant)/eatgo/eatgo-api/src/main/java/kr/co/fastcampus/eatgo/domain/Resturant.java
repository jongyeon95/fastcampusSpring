package kr.co.fastcampus.eatgo.domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Resturant {
    private  final String name;
    private  final String address;
    private  final Long id;
    private List<MenuItem> menuItems=new ArrayList<>();

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
