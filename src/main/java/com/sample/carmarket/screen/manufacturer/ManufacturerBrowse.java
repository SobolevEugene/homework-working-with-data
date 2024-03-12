package com.sample.carmarket.screen.manufacturer;

import com.sample.carmarket.app.CarService;
import com.sample.carmarket.entity.EngineType;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {
	@Autowired
	private Table<Manufacturer> table;
	@Autowired
	private CarService carService;
	@Autowired
	private Notifications notifications;

	@Subscribe("table.calculateCars")
	public void onTableCalculateCars(final Action.ActionPerformedEvent event) {
		if (table.getSingleSelected() == null) {
			return;
		}
		var cars = carService.getCarsByManufacturer(table.getSingleSelected());
		long electricCars = cars.stream().filter(car -> car.getModel().getEngineType().equals(EngineType.ELECTRIC)).count();
		long gasolineCars = cars.stream().filter(car -> car.getModel().getEngineType().equals(EngineType.GASOLINE)).count();
		notifications.create().withCaption("Electric cars: " + electricCars + ", gasoline cars: " + gasolineCars).show();
	}
}