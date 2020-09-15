package myapp.pojo;

import lombok.Data;

@Data
public class Car {
	private Long id;
    private String name;

    public Long getId() {
    	return id;
    }
    
	public String getName() {
		return name;
	}
}
