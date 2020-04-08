package edu.szymonr.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
