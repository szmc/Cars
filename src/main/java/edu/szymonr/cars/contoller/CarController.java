package edu.szymonr.cars.contoller;

import edu.szymonr.cars.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {
    CarApi carApi;

    @Autowired
    public CarController(CarApi carApi) {
        this.carApi = carApi;
    }

    @GetMapping("/car")
    public String getCar(Model model) {
        model.addAttribute("cars", carApi.getAllCars().getBody());
        model.addAttribute("newCar", new Car());
        return "car";
    }

    @GetMapping("/add-car")
    public String addCar(Model model){
        model.addAttribute("newCar", new Car());
        return "add-car";
    }
    @PostMapping("/add-car")
    public String addCar(@ModelAttribute Car car, Model model) {
        model.addAttribute("newCar", new Car());
        carApi.addCar(car);
        return "redirect:/car";
    }

    @RequestMapping ("/edit-car/{id}")
    public String editCar   (Model model, @PathVariable long id) {
        model.addAttribute("car", getCarById(id));
        return "edit-car";
    }

    @PostMapping ("/edit-car")
    public String editCar(@ModelAttribute Car car) {
        carApi.modifyCar(car);
        return "redirect:/car";
    }

   @GetMapping("/view-car/{id}")
   public String  getCarById(Model model, @PathVariable long id){
        model.addAttribute("car", getCarById(id));
        return "view-car";
   }

    @PostMapping("/delete-car/{id}")
    public String  deleteCar(@PathVariable long id){
        carApi.deleteCarById(id);
        return "redirect:/car";
    }

    @GetMapping("/change-color/{id}")
    public String changeColor(Model model, @PathVariable long id) {
        model.addAttribute("car", getCarById(id));
        return "change-color";
    }

    @PostMapping("/change-color")
    public String changeColor(@ModelAttribute Car car){
        carApi.modifyColor(car.getId(), car.getColor());
        return "redirect:/car";
    }

    private Car getCarById(long id) {
        return carApi.getCarById(id).getBody();
    }
}
