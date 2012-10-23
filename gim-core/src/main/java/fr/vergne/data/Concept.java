package fr.vergne.data;

public abstract class Concept {

	private Object representation;

	public Object getRepresentation() {
		return representation;
	}

	public void setRepresentation(Object representation) {
		this.representation = representation;
	}

	public boolean hasRepresentation() {
		return representation != null;
	}

	@Override
	public String toString() {
		return (hasRepresentation() ? getRepresentation().toString() : "")
				+ "[" + hashCode() + "]";
	}
}
