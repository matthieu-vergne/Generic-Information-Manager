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
			systemFactory.makeIsAvailableToRelation(concept, creator);
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
		Property property = new RepresentationProperty(representation);
		initConcept(property);
		return property;
	}

	public Relation makeRelation(Concept start, Concept end) {
		Relation relation = new Relation();
		relation.setStart(start);
		relation.setEnd(end);
		initConcept(relation);
		return relation;
	}

	public Relation makeWasCreatedByRelation(Concept created, Concept creator) {
		Relation relation = makeRelation(created, creator);
		makeRelation(relation, container.getWasCreatedByRelationType());
		return relation;
	}

	public Relation makeIsAvailableToRelation(Concept concept, Concept owner) {
		Relation relation = makeRelation(concept, owner);
		makeRelation(relation, container.getWasCreatedByRelationType());
		return relation;
	}

	public Relation makeWasCreatedOnRelation(Concept created, Date date) {
		Relation relation = makeRelation(created, makeProperty(date));
		makeRelation(relation, container.getWasCreatedOnRelationType());
		return relation;
	}

	public Relation makeIsRepresentedByRelation(Concept represented, Object representer) {
		Relation relation = makeRelation(represented, makeProperty(representer));
		makeRelation(relation, container.getWasCreatedOnRelationType());
		return relation;
	}
}
