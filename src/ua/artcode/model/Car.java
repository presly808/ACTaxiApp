package ua.artcode.model;

import java.io.Serializable;

/**
 * Created by ivan on 20.12.15.
 */
public class Car implements Serializable {

    private String car;
    private String numb;
    private String color;

    public Car(String car, String numb, String color) {
        this.car = car;
        this.numb = numb;
        this.color = color;
    }

    @Override
    public String toString() {
        return "["+car +
                ", " + numb +
                ", " + color+"]";
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getNumb() {
        return numb;
    }

    public void setNumb(String numb) {
        this.numb = numb;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object car){

        if(car == null){
            return false;
        }
        if(this == car){
            return true;
        }
        if(car.getClass() != Car.class){
            return false;
        }
        Car tmp = (Car) car;

        return color.equals(tmp.color) && (this.car.equals(tmp.car) && numb == tmp.numb);
    }
}
