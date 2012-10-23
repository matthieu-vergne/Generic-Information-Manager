package fr.vergne.data.relation;

import fr.vergne.data.Concept;
import fr.vergne.data.property.TextProperty;

public class IsRepresentedByRelation extends Relation<Concept, TextProperty> {

	public IsRepresentedByRelation(Concept concept, TextProperty text) {
		super(concept, text);
	}

	public IsRepresentedByRelation(Concept concept, String text) {
		super(concept, new TextProperty(text));
	}
}
