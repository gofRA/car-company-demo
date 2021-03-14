package ru.rdanilov.car.service;

import org.springframework.data.jpa.domain.Specification;
import ru.rdanilov.car.model.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAll(Specification<Vehicle> specification, String sorting);
}
