package com.rushabh.ecsdemo.controller;

import com.rushabh.ecsdemo.entity.Car;
import com.rushabh.ecsdemo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(path = "/car", method = RequestMethod.POST)
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car addedCar = carService.addCar(car);
        return new ResponseEntity(addedCar, HttpStatus.OK);
    }

    @RequestMapping(path = "/car/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Car> updateCar(@PathVariable int id, @RequestBody Car car){
        Car updatedCar = carService.updateCar(id, car);
        return new ResponseEntity(updatedCar, HttpStatus.OK);
    }

    @RequestMapping(path = "/car", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getCars(){
        List<Car> lstCar = carService.getCars();
        return new ResponseEntity(lstCar, HttpStatus.OK);
    }

    @RequestMapping(path = "/car/{id}", method = RequestMethod.GET)
    public ResponseEntity<Car> getCar(@PathVariable int id){
        Car car = carService.getCar(id).get();
        return new ResponseEntity(car, HttpStatus.OK);
    }

    @RequestMapping(path = "/car/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteCar(@PathVariable int id){
        boolean deleteFlag = carService.deleteCar(id);
        return new ResponseEntity(deleteFlag, HttpStatus.OK);
    }

}
