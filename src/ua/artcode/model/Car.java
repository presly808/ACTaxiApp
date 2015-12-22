package ua.artcode.model;

import java.io.Serializable;

/**
 * Created by ivan on 20.12.15.
 */
public class Car implements Serializable {
    private String car;
    private int numb;
    private String color;

    public Car(String car, int numb, String color) {
        this.car = car;
        this.numb = numb;
        this.color = color;
    }

    public Car(String carInit){

        String[] parameters = car.split("|");

        car = parameters[0];
        numb = new Integer(parameters[1]);
        color = parameters[2];

    }

    @Override
    public String toString() {
        return car +
                "|" + numb +
                "|" + color;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
