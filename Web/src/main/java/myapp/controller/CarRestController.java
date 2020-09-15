package myapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import myapp.pojo.Car;

@RestController
@RequestMapping("cars")
public class CarRestController {
	private final CarClient carClient;
	
	public CarRestController(CarClient carClient) {
		this.carClient = carClient;
	}
	
	private Collection<Car> fallback() {
        return new ArrayList<>();
    }
	
	private String stringFallback() {
		return "ERROR";
	}
	
	@GetMapping("getAllCars")
	@CrossOrigin
    @HystrixCommand(fallbackMethod = "fallback")
	public Collection<Car> getAllCars() {
		return carClient.readCars().getContent().stream().collect(Collectors.toList());
	}
	
	@GetMapping("getCoolCars")
	@CrossOrigin
	@HystrixCommand(fallbackMethod = "fallback")
	public Collection<Car> getCoolCars() {
		return carClient.readCoolCars().stream().collect(Collectors.toList());
	}
	
	@PostMapping("addCar")
    @CrossOrigin
    public String addCar(@RequestBody(required = false) MultiValueMap<String, String> values) {
		return carClient.addCar(values);
    }
	
	@DeleteMapping("removeCar/{carId}")
	@CrossOrigin
	@HystrixCommand(fallbackMethod = "stringFallback")
	public String removeCar(@PathVariable("carId") String carId) {
		String result = carClient.removeCar(carId);
	    if(result.equals(result)) {
	    	return carId;
	    }
	    
	    return "-1";
	}
}

@FeignClient("api-gateway")
interface CarClient {

	@GetMapping("/car-service/cars")
	@CrossOrigin
	CollectionModel<Car> readCars();

	@GetMapping("/cool-cars")
	@CrossOrigin
	Collection<Car> readCoolCars();
	
	@PostMapping("/car-service/addCar")
	@CrossOrigin
	String addCar(MultiValueMap<String, String> values);
	
	@DeleteMapping("/car-service/removeCar/{carId}")
	@CrossOrigin
	String removeCar(@PathVariable("carId") String carId);
}
