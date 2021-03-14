package ru.rdanilov.car.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "vehicle_brand_id")
    private VehicleBrand vehicleBrand;

    @Column(name = "model")
    private String model;

    @Column(name = "year_of_issue")
    private Integer yearOfIssue;

    @Column(name = "gas_type")
    private String gasType;
}
