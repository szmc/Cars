package edu.szymonr.cars;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarApi {

    private List<Car> carList;

    public CarApi() {
        this.carList = new ArrayList<>();
        carList.add(new Car(1l, "Chevrolet", "Aveo", "Grey"));
        carList.add(new Car(2l, "Kia", "Ceed", "Black"));
        carList.add(new Car(3l, "Fiat", "Panda", "Yellow"));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> first = carList.stream().filter(car -> car.getId() == id).findFirst();
        if(first.isPresent()) {
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color")
    public ResponseEntity<List<Car>> getCarsByColor(@RequestParam String color) {
        List<Car> getAllCarsWithColor = carList.stream().filter(car -> car.getColor().equals(color)).collect(Collectors.toList());
        if(!getAllCarsWithColor.isEmpty()) {
            return new ResponseEntity<>(getAllCarsWithColor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        boolean add = carList.add(car);
        if(add) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity modifyCar(@RequestBody Car newCar) {
        Optional<Car> first = carList.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
        if(first.isPresent()) {
            carList.remove(first.get());
            carList.add(newCar);
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping
    public ResponseEntity<Car> modifyColor(@RequestParam Long id, @RequestParam String newColor) {
        Optional<Car> modyfiedCar = carList.stream().filter(car -> car.getId() == id).findFirst();
        if(modyfiedCar.isPresent()) {
            modyfiedCar.get().setColor(newColor);
            return new ResponseEntity<>(modyfiedCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCarById(@RequestParam long id) {
        Optional<Car> first = carList.stream().filter(car -> car.getId() == id).findFirst();
        if(first.isPresent()) {
            carList.remove(first.get());
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
