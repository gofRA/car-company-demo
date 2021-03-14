package ru.rdanilov.car.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rdanilov.car.model.Vehicle;
import ru.rdanilov.car.service.VehicleService;
import ru.rdanilov.car.specification.VehicleSpecification;

import java.util.List;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> findAll(VehicleSpecification specification, @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sorting) {
        return vehicleService.findAll(specification, sorting);
    }
}
