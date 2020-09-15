package myapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import myapp.pojo.Car;

@Controller
public class IndexController {
	private final CarClient carClient;
	
	public IndexController(CarClient carClient) {
		this.carClient = carClient;
	}
	
	private Collection<Car> fallback() {
        return new ArrayList<>();
    }
	
    @GetMapping("index")
	public String index() {
		return "index";
	}
    
    @GetMapping("carsPage")
    public String carsPage(Model model) {
    	List<Car> cars = carClient.readCars().getContent().stream().collect(Collectors.toList());
    	model.addAttribute("cars", cars);
    	return "carsPage";
    }
}
