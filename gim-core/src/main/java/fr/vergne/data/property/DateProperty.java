package fr.vergne.data.property;

import java.util.Date;

public class DateProperty extends Property<Date> {
	public DateProperty(Date date) {
		set(date);
	}

	public DateProperty() {
		set(new Date());
	}
}
