package ru.rdanilov.car.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "vehicle_type")
public class VehicleType implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

}
