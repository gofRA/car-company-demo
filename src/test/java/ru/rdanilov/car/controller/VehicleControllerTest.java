package ru.rdanilov.car.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testWithoutFilters() throws Exception {
        mockMvc.perform(get("/vehicle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(11)));
    }

    @Test
    public void testFilterByBus() throws Exception {
        String paramString = "vehicleType.name=bus";
        mockMvc.perform(get("/vehicle?" + paramString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].model", containsInAnyOrder("Ikarus-489", "Ikarus-435T", "Ducato")));
    }

    @Test
    public void testFilterByBrandAndModel() throws Exception {
        String paramString = "vehicleBrand.name=bmw&model=x6";
        mockMvc.perform(get("/vehicle?" + paramString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(9)))
                .andExpect(jsonPath("$[0].vehicleType.name", is("Car")))
                .andExpect(jsonPath("$[0].vehicleBrand.name", is("BMW")))
                .andExpect(jsonPath("$[0].model", is("x6")))
                .andExpect(jsonPath("$[0].yearOfIssue", is(2012)))
                .andExpect(jsonPath("$[0].gasType", is("DIESEL")));
    }

    @Test
    public void testFilterByYearOfIssueAndGasType() throws Exception {
        String paramString = "yearOfIssue=2020&gasType=petrol";
        mockMvc.perform(get("/vehicle?" + paramString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(4)))
                .andExpect(jsonPath("$[0].vehicleType.name", is("Motorbike")))
                .andExpect(jsonPath("$[0].vehicleBrand.name", is("Yamaha")))
                .andExpect(jsonPath("$[0].model", is("R1")))
                .andExpect(jsonPath("$[0].yearOfIssue", is(2020)))
                .andExpect(jsonPath("$[0].gasType", is("PETROL")));
    }


    @Test
    public void testFilterByAllParams() throws Exception {
        String paramString = "vehicleType.name=bus&vehicleBrand.name=fiat&model=ducato&yearOfIssue=2014&gasType=PROPANE";
        mockMvc.perform(get("/vehicle?" + paramString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(10)))
                .andExpect(jsonPath("$[0].vehicleType.name", is("Bus")))
                .andExpect(jsonPath("$[0].vehicleBrand.name", is("Fiat")))
                .andExpect(jsonPath("$[0].model", is("Ducato")))
                .andExpect(jsonPath("$[0].yearOfIssue", is(2014)))
                .andExpect(jsonPath("$[0].gasType", is("PROPANE")));
    }

    @Test
    public void testFindAllWithDefaultSorting() throws Exception {
        mockMvc.perform(get("/vehicle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(11)))
                .andExpect(jsonPath("$[*].id", contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)));
    }

    @Test
    public void testFindWithSortingByBrandAscAndYearOfIssueDesc() throws Exception {
        String paramString = "vehicleType.name=car&sortBy=vehicleBrand.name,yearOfIssue:desc";
        mockMvc.perform(get("/vehicle?" + paramString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[*].id", contains(7, 9, 8, 11, 1)))
                .andExpect(jsonPath("[*].vehicleBrand.name", contains("BMW", "BMW", "BMW", "Honda", "Skoda")))
                .andExpect(jsonPath("[*].yearOfIssue", contains(2021, 2012, 2010, 2013, 2008)));
    }

    @Test
    public void testFindWithSortingByAllParamsDescReversed() throws Exception {
        String paramString = "&sortBy=gasType:desc,yearOfIssue:desc,model:desc,vehicleBrand.name:desc,vehicleType.name:desc";
        mockMvc.perform(get("/vehicle?" + paramString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(11)))
                .andExpect(jsonPath("$[*].id", contains(2, 10, 6, 5, 7, 4, 11, 1, 3, 9, 8)))
                .andExpect(jsonPath("$[*].gasType",
                        contains("PROPANE", "PROPANE", "PETROL", "PETROL", "PETROL", "PETROL", "PETROL", "PETROL", "DIESEL", "DIESEL", "DIESEL")))
                .andExpect(jsonPath("$[*].yearOfIssue", contains(2016, 2014, 2021, 2021, 2021, 2020, 2013, 2008, 2020, 2012, 2010)))
                .andExpect(jsonPath("$[*].model",
                        contains("Ikarus-489", "Ducato", "R6 RACE", "R1M", "M3", "R1", "Civic", "Rapid", "Ikarus-435T", "x6", "x5")))
                .andExpect(jsonPath("$[*].vehicleBrand.name",
                        contains("Ikarus", "Fiat", "Yamaha", "Yamaha", "BMW", "Yamaha", "Honda", "Skoda", "Ikarus", "BMW", "BMW")))
                .andExpect(jsonPath("$[*].vehicleType.name",
                        contains("Bus", "Bus", "Motorbike", "Motorbike", "Car", "Motorbike", "Car", "Car", "Bus", "Car", "Car")));
    }

    @Test
    public void testInvalidFilterValue() throws Exception {
        String paramString = "vehicleType.name=buss";
        mockMvc.perform(get("/vehicle?" + paramString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testInvalidFilterParam() throws Exception {
        String paramString = "justType";
        mockMvc.perform(get("/vehicle?" + paramString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(11)));
    }

    @Test
    public void testInvalidSorting() throws Exception {
        String paramString = "sortBy=type";
        mockMvc.perform(get("/vehicle?" + paramString))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", is("No property type found for type Vehicle!")));
    }

    @Test
    public void testInvalidSortingDirection() throws Exception {
        String paramString = "sortBy=gasType:up";
        mockMvc.perform(get("/vehicle?" + paramString))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", is("Invalid value 'up' for orders given! Has to be either 'desc' or 'asc' (case insensitive).")));
    }
}
