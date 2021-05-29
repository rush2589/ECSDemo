package com.rushabh.ecsdemo.service;

import com.rushabh.ecsdemo.entity.Car;
import com.rushabh.ecsdemo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public Car addCar(Car customer) {
        return repository.save(customer);
    }

    public Car updateCar(int id, Car customer){
        if(getCar(id).isPresent()) {
            customer.setId(id);
            return repository.save(customer);
        }
        else
            return null;
    }

    public List<Car> getCars() {
        List<Car> lst = repository.findAll();
        if (lst.isEmpty())
            return new ArrayList<>();
        return lst;
    }

    public Optional<Car> getCar(int id){
        Optional<Car> optional = repository.findById(id);
        if (optional.isPresent())
            return optional;
        return Optional.empty();
    }

    public boolean deleteCar(int id){
        if(getCar(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
