package com.sample.carmarket.app;

import com.sample.carmarket.entity.Car;
import com.sample.carmarket.entity.Manufacturer;
import io.jmix.core.DataManager;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CarService {
	private final DataManager dataManager;

	public CarService(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	public List<Car> getCarsByManufacturer(Manufacturer manufacturer) {
		if (manufacturer == null) {
			return Collections.emptyList();
		}

		return dataManager.load(Car.class)
				.query("select c from Car c where c.model.manufacturer.id = :manufacturerId")
				.parameter("manufacturerId", manufacturer.getId())
				.list();

	}
}