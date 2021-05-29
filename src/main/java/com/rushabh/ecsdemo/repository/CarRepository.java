package com.rushabh.ecsdemo.repository;

import com.rushabh.ecsdemo.entity.Car;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{

}
