package org.richfaces.openhouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class RichBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;

    private String name;
    
    private List<Car> cars;

	public class Car {
    	
    	private String vendor;
    	private Integer price;
    	private String maxSpeed;
    	
    	public Car(String vendor, Integer price, String maxSpeed) {
			super();
			this.vendor = vendor;
			this.price = price;
			this.maxSpeed = maxSpeed;
		}
		public String getVendor() {
			return vendor;
		}
		public void setVendor(String vendor) {
			this.vendor = vendor;
		}
		public Integer getPrice() {
			return price;
		}
		public void setPrice(Integer price) {
			this.price = price;
		}
		public String getMaxSpeed() {
			return maxSpeed;
		}
		public void setMaxSpeed(String maxSpeed) {
			this.maxSpeed = maxSpeed;
		}
    }

    @PostConstruct
    public void postContruct() {
        name = "John";
        
        cars = new ArrayList<Car>();
        
        cars.add(new Car("Porsche Carrera GT", 2000, "320"));
        cars.add(new Car("Skoda Fabia", 1000, "180"));
        cars.add(new Car("Fiat turbo", 14500, "199"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}