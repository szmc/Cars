package edu.szymonr.cars.contoller;

import edu.szymonr.cars.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarApi {
    CarService carService;

    @Autowired
    public CarApi(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carService.getCarList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> first = carService.filterCarById(id);
        if(carService.idOfACarIsOnTheList(id)) {
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

     @GetMapping("/color")
    public ResponseEntity<List<Car>> getCarsByColor(@RequestParam String color) {
        List<Car> getAllCarsWithColor = carService.filterCarColor(color);
        if(!getAllCarsWithColor.isEmpty()) {
            return new ResponseEntity<>(getAllCarsWithColor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        boolean add = carService.addCar(car);
        if(add) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity modifyCar(@RequestBody Car newCar) {
        Optional<Car> first = carService.filterCarById(newCar.getId());//carList.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
        if(carService.idOfACarIsOnTheList(newCar.getId())) {
            carService.modifyCar(first.get(), newCar);
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping
    public ResponseEntity<Car> modifyColor(@RequestParam Long id, @RequestParam String newColor) {
        Optional<Car> modyfiedCar = carService.filterCarById(id);
        if(carService.idOfACarIsOnTheList(id)) {
            carService.setColor(modyfiedCar, newColor);
            return new ResponseEntity<>(modyfiedCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable long id) {
        Optional<Car> first = carService.filterCarById(id);
        if(carService.idOfACarIsOnTheList(id)) {
            carService.removeCar(first.get());
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
