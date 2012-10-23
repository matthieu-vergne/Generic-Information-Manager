package fr.vergne.data.relation;

import fr.vergne.data.Concept;
import fr.vergne.data.property.DateProperty;

public class IsCreatedOnRelation extends Relation<Concept, DateProperty> {

	public IsCreatedOnRelation(Concept concept, DateProperty creationDate) {
		super(concept, creationDate);
	}

}
