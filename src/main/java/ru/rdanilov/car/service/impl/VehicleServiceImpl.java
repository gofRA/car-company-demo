package ru.rdanilov.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.rdanilov.car.model.Vehicle;
import ru.rdanilov.car.repository.VehicleRepository;
import ru.rdanilov.car.service.VehicleService;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(@Autowired VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> findAll(Specification<Vehicle> specification, String sorting) {
        return vehicleRepository.findAll(specification, getSorting(sorting));
    }

    private Sort getSorting(String sorting) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String s : sorting.split(",")) {
            String [] parts = s.split(":");
            if (parts.length == 2) {
                orders.add(new Sort.Order(Sort.Direction.fromString(parts[1]), parts[0]));
            } else if (parts.length == 1) {
                orders.add(new Sort.Order(parts[0]));
            }
        }
        return new Sort(orders);
    }
}
