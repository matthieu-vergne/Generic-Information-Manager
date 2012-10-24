package fr.vergne.data;

public class RepresentationProperty extends Property {

	private Object representation;

	public RepresentationProperty(Object representation) {
		this.representation = representation;
	}

	public Object getRepresentation() {
		return representation;
	}

}
