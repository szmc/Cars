package edu.szymonr.cars.model;

public class Car {
    long id;
    String mark;
    String model;
    String color;

    public Car(long id, String mark, String model, String color) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public Car(String mark, String model, String color) {
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public Car() {
    }

    public long getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
