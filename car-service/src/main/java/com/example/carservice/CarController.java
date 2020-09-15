package com.example.carservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
	@Autowired
	private CarRepository carRepository;

	@PostMapping("/addCar")
	public String addCar(@RequestBody(required = false) MultiValueMap<String, String> values) {
		Car car = new Car();
        car.setName(values.getFirst("carName"));
        carRepository.save(car);
        
        return "Auto totali: " + carRepository.count();
	}
	
	@DeleteMapping("/removeCar/{carId}")
	public String removeCar(@PathVariable("carId") String carId) {
		carRepository.deleteById(Long.parseLong(carId));
		return "OK";
	}
}