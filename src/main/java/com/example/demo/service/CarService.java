package com.example.demo.service;


import com.example.demo.dao.CarsRepository;
import com.example.demo.exception.SortException;
import com.example.demo.model.Car;
import com.example.demo.service.sort_properties.CarsSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarsSort carsSort;

    @Autowired
    private CarsRepository carsRepository;

    @Value("${maxCar}")
    private Long maxCar;

    @ExceptionHandler(SortException.class)
    public List<Car> getCars(String sortBy, Optional<Long> count) {
        long countMaxCar = count.filter(x -> x < maxCar).orElse(0L);
        long pageable = countMaxCar + 1L;

        if (countMaxCar == 0) {
            if (sortBy != null) {
                if (isSortEnabled(sortBy)) {
                    throw new SortException();
                }
                return carsRepository.findAll(Sort.by(sortBy));
            }
            return carsRepository.findAll();
        }

        if (sortBy != null) {
            if (isSortEnabled(sortBy)) {
                throw new SortException();
            }
            return carsRepository.findAllByIdBefore(pageable, Sort.by(sortBy));
        }
        return carsRepository.findAllByIdBefore(pageable);
    }

    public boolean isSortEnabled(String sortBy) {
        return !carsSort.getSortBy().contains(sortBy);
    }
}