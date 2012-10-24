package fr.vergne.data;

public abstract class Concept {

	@Override
	public String toString() {
		// TODO recover representation from representation relations
		return "[" + hashCode() + "]";
	}
}
