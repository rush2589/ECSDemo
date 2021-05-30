package com.rushabh.ecsdemo.service;

import com.rushabh.ecsdemo.entity.Car;
import com.rushabh.ecsdemo.entity.Make;
import com.rushabh.ecsdemo.entity.Model;
import com.rushabh.ecsdemo.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceTest {

    @InjectMocks
    CarService carService;

    @Mock
    CarRepository carRepository;

    @BeforeAll
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    private Car getCarObj(int id){
        Car car = new Car();
        car.setId(id);
        car.setColour("Metalic Grey");
        //car.setMake("Hyundai");
        //car.setModel("I20");
        car.setYear(2019);

        Make make = new Make();
        make.setName("Hyundai");

        Model model = new Model();
        model.setModel("I20");
        model.setMake(make);

        car.setModel(model);

        return car;
    }

    @Test
    public void test1_getCarById_noMatch(){
        Mockito.when(carRepository.findById((Mockito.any()))).thenReturn(Optional.empty());

        Optional<Car> car = carService.getCar(112);
        Assertions.assertEquals(false, car.isPresent());
    }

    @Test
    public void test2_placeCar(){

        //Mocking repo OBJ
        Mockito.when(carRepository.save((Mockito.any()))).thenReturn(getCarObj(1));

        Car addCar = carService.addCar(getCarObj(1));
        Assertions.assertNotEquals(0, addCar.getId());
    }

    @Test
    public void test3_getCarById_match(){
        //Mocking Repo Object
        Mockito.when(carRepository.findById((Mockito.any()))).thenReturn(Optional.of(getCarObj(3)));

        Optional<Car> car = carService.getCar(3);
        Assertions.assertEquals(true, car.isPresent());
    }

    @Test
    public void test4_getAllCars_match(){
        List<Car> lst = new ArrayList<Car>();
        lst.add(getCarObj(4));

        //Mocking Repo Object
        Mockito.when(carRepository.findAll()).thenReturn(lst);

        List<Car> lstCars = carService.getCars();
        Assertions.assertEquals(true, lstCars.size()>0);
    }

    @Test
    public void test5_deleteCars(){
        carService.deleteCar(2);
    }
}
