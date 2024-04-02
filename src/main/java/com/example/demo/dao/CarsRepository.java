package com.example.demo.dao;

import com.example.demo.model.Car;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByIdBefore(Long id, Sort sort);
    List<Car> findAllByIdBefore(Long id);

}
