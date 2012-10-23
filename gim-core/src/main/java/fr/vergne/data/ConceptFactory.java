package fr.vergne.data;

import java.util.Date;

public class ConceptFactory {

	private final ConceptContainer container;
	private final Concept creator;
	private boolean decorationsActivated;

	public ConceptFactory(ConceptContainer container, Concept creator) {
		this.creator = creator;
		this.container = container;
	}

	public void setDecorationsActivated(boolean decorations) {
		this.decorationsActivated = decorations;
	}

	public boolean getDecorationsActivated() {
		return decorationsActivated;
	}

	private void initConcept(Concept concept) {
		container.addConcept(concept);
		if (getDecorationsActivated()) {
			ConceptFactory systemFactory = container.getSystemConceptFactory();
			boolean old = systemFactory.getDecorationsActivated();
			systemFactory.setDecorationsActivated(false);
			systemFactory.makeWasCreatedByRelation(concept, creator);
			systemFactory.makeWasCreatedOnRelation(concept, new Date());
			systemFactory.setDecorationsActivated(old);
		} else {
			// do not put decorations
		}
	}

	public Property makeProperty() {
		Property property = new Property();
		initConcept(property);
		return property;
	}

	public Property makeProperty(Object representation) {
		Property property = makeProperty();
		property.setRepresentation(representation);
		return property;
	}

	public Relation makeRelation(Concept start, Concept end) {
		Relation relation = new Relation();
		relation.setStart(start);
		relation.setEnd(end);
		initConcept(relation);
		return relation;
	}

	public Relation makeRelation(Concept start, Concept end,
			Object representation) {
		Relation relation = makeRelation(start, end);
		relation.setRepresentation(representation);
		return relation;
	}

	public Relation makeWasCreatedByRelation(Concept created, Concept creator) {
		Relation relation = makeRelation(created, creator);
		relation.setRepresentation("was created by");
		return relation;
	}

	public Relation makeWasCreatedOnRelation(Concept created, Date date) {
		Relation relation = makeRelation(created, makeProperty(date));
		relation.setRepresentation("was created on");
		return relation;
	}

	public Relation makeIsInstanceOfRelation(Concept instance, Concept type) {
		Relation relation = makeRelation(instance, type);
		relation.setRepresentation("is instance of");
		return relation;
	}
}
