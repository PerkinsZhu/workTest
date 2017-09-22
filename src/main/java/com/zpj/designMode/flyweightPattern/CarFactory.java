package com.zpj.designMode.flyweightPattern;

import java.util.HashMap;
import java.util.Map;

public class CarFactory {

	Map<String, Car> cars = new HashMap<String, Car>();

	public Car getCar(String type) {
		Car car = cars.get(type);
		if (car == null) {
			Car newCar = new Car(type);
			cars.put(type, newCar);
			return newCar;
		}
		return car;
	}
}
