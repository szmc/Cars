package edu.szymonr.cars.contoller;

import edu.szymonr.cars.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    private List<Car> carList;

    @Autowired
    public CarService() {
        this.carList = new ArrayList<>();
        carList.add(new Car(1l, "Chevrolet", "Aveo", "Grey"));
        carList.add(new Car(2l, "Kia", "Ceed", "Black"));
        carList.add(new Car(3l, "Fiat", "Panda", "Yellow"));
    }

    public List<Car> getCarList() {
        return this.carList;
    }

    public boolean addCar(Car car){
         return carList.add(new Car(setId(), car.getMark(), car.getModel(), car.getColor()));
    }

    public Car modifyCar(Car car, Car newCar) {
        car.setMark(newCar.getMark());
        car.setModel(newCar.getModel());
        car.setColor(newCar.getColor());
        return car;
    }

    public boolean idOfACarIsOnTheList(long id) {
        return filterCarById(id).isPresent();
    }

    public Optional<Car> filterCarById(long id){
        return carList.stream().filter(car -> car.getId() == id).findFirst();
    }

    public List<Car> filterCarColor(String color){
        return carList.stream().filter(car -> car.getColor() == color).collect(Collectors.toList());
    }

    public void setColor(Optional<Car> car, String color) {
        car.get().setColor(color);
    }

    public void removeCar(Car car) {
        carList.remove(car);
    }

    public long setId() {
        if(carList.isEmpty()){
            return 1;
        } else {
            return carList.get(carList.size() - 1).getId() + 1;
        }
    }
}
