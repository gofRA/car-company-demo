package ru.rdanilov.car.specification;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.JoinFetch;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Joins;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import ru.rdanilov.car.model.Vehicle;

@Joins(value = {
        @Join(path = "vehicleType", alias = "vehicleType"),
        @Join(path = "vehicleBrand", alias = "vehicleBrand"),
    },
       fetch = {
        @JoinFetch(paths = {"vehicleBrand"}),
        @JoinFetch(paths = {"vehicleType"})
})
@And({
        @Spec(path = "vehicleType.name", params = "vehicleType.name", spec = EqualIgnoreCase.class),
        @Spec(path = "vehicleBrand.name", params = "vehicleBrand.name", spec = EqualIgnoreCase.class),
        @Spec(path = "model", params = "model", spec = EqualIgnoreCase.class),
        @Spec(path = "gasType", params = "gasType", spec = EqualIgnoreCase.class),
        @Spec(path = "yearOfIssue", params = "yearOfIssue", spec = Equal.class)
})
public interface VehicleSpecification extends Specification<Vehicle> {
}
