package kr.co.fastcampus.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resturant {

    @Id
    @GeneratedValue
    private   Long id;
    private   String name;
    private   String address;

    @Transient
    private List<MenuItem> menuItems;


    public String getInformation() {
        return  name + " in "+address;
    }




    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems= new ArrayList<>(menuItems);

    }



    public void updateInformation(String name, String address) {
        this.name=name;
        this.address=address;
    }
}
