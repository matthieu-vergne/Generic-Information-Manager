package fr.vergne.data.property;

import fr.vergne.data.Concept;

public class Property<DataType> extends Concept {

	private DataType data;

	public DataType get() {
		return data;
	}

	public void set(DataType data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return data.toString();
	}
}
