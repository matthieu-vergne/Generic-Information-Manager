package fr.vergne.data.relation;

import fr.vergne.data.Concept;
import fr.vergne.data.property.DateProperty;

public class Relation<DataType1 extends Concept, DataType2 extends Concept>
		extends Concept {

	private DataType1 start;
	private DataType2 end;

	public Relation(DataType1 start, DataType2 end) {
		setStart(start);
		setEnd(end);
		addRelation(new IsCreatedOnRelation(this, new DateProperty()));
	}

	public DataType1 getStart() {
		return start;
	}

	public void setStart(DataType1 start) {
		if (start == null) {
			throw new RuntimeException("You must give a start element");
		} else {
			this.start = start;
			start.addRelation(this);
		}
	}

	public DataType2 getEnd() {
		return end;
	}

	public void setEnd(DataType2 end) {
		if (end == null) {
			throw new RuntimeException("You must give an end element");
		} else {
			this.end = end;
			end.addRelation(this);
		}
	}

	@Override
	public String toString() {
		String s1 = "(" + start.toString() + ")";
		String s2 = "(" + end.toString() + ")";
		return s1 + "--" + getRepresentation() + "->" + s2;
	}
}
