package com.sample.carmarket.screen.car;

import com.sample.carmarket.app.CarService;
import com.sample.carmarket.entity.CarStatus;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Car.browse")
@UiDescriptor("car-browse.xml")
@LookupComponent("carsTable")
public class CarBrowse extends StandardLookup<Car> {
	@Autowired
	private GroupTable<Car> carsTable;
	@Autowired
	private Notifications notifications;
	@Autowired
	private CarService carService;

	@Subscribe("carsTable.markAsSold")
	public void onCarsTableMarkAsSold(final Action.ActionPerformedEvent event) {
		var selectedCar = carsTable.getSingleSelected();
		if (selectedCar==null) {
			return;
		}

		if (selectedCar.getStatus().equals(CarStatus.SOLD)) {
			notifications.create().withCaption("Already Sold").show();
		}

		if (selectedCar.getStatus().equals(CarStatus.IN_STOCK)) {
			carService.markCarAsSold(selectedCar);
			notifications.create().withCaption("Done").show();
		}
	}
}