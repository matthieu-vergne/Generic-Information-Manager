package fr.vergne.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ConceptContainer {

	private final Set<Concept> concepts = new HashSet<Concept>();
	private final Property system;
	private final Property owner;
	private final ConceptFactory systemFactory;
	private final ConceptFactory ownerFactory;
	private final Property conceptType;
	private final Property propertyType;
	private final Property relationType;
	private final Property wasCreatedByRelationType;
	private final Property wasCreatedOnRelationType;
	private final Property isAvailableToRelationType;

	public ConceptContainer() {
		system = new Property();
		addConcept(system);
		systemFactory = new ConceptFactory(this, system);
		systemFactory.setDecorationsActivated(false);
		systemFactory.makeWasCreatedByRelation(system, system);
		systemFactory.makeWasCreatedOnRelation(system, new Date());
		systemFactory.makeIsRepresentedByRelation(system, "system");
		systemFactory.setDecorationsActivated(true);

		conceptType = systemFactory.makeProperty();
		systemFactory.makeRelation(conceptType, conceptType);
		systemFactory.makeIsRepresentedByRelation(conceptType, "concept");

		propertyType = systemFactory.makeProperty();
		systemFactory.makeRelation(propertyType, conceptType);
		systemFactory.makeIsRepresentedByRelation(propertyType, "property");
		relationType = systemFactory.makeProperty();
		systemFactory.makeRelation(relationType, conceptType);
		systemFactory.makeIsRepresentedByRelation(relationType, "relation");

		wasCreatedByRelationType = systemFactory.makeProperty();
		systemFactory.makeRelation(wasCreatedByRelationType, relationType);
		systemFactory.makeIsRepresentedByRelation(wasCreatedByRelationType,
				"was created by");
		wasCreatedOnRelationType = systemFactory.makeProperty();
		systemFactory.makeRelation(wasCreatedOnRelationType, relationType);
		systemFactory.makeIsRepresentedByRelation(wasCreatedOnRelationType,
				"was created on");
		isAvailableToRelationType = systemFactory.makeProperty();
		systemFactory.makeRelation(isAvailableToRelationType, relationType);
		systemFactory.makeIsRepresentedByRelation(isAvailableToRelationType,
				"is available to");
		

		owner = systemFactory.makeProperty("you");
		systemFactory.makeIsAvailableToRelation(owner, owner);
		ownerFactory = new ConceptFactory(this, owner);

		Property ownerType = systemFactory.makeProperty("owner");
		systemFactory.makeRelation(owner, ownerType);
		Property single = systemFactory.makeProperty("1");
		systemFactory.makeRelation(ownerType, single);
		systemFactory.makeIsRepresentedByRelation(wasCreatedByRelationType,
				"has exact number of instances of");
	}

	public void addConcept(Concept concept) {
		concepts.add(concept);
	}

	public void removeConcept(Concept concept) {
		concepts.remove(concept);
	}

	public Set<Concept> getConcepts() {
		return concepts;
	}

	public Property getSystem() {
		return system;
	}

	public Property getOwner() {
		return owner;
	}

	public ConceptFactory getSystemConceptFactory() {
		return systemFactory;
	}

	public ConceptFactory getOwnerConceptFactory() {
		return ownerFactory;
	}

	public Property getWasCreatedByRelationType() {
		return wasCreatedByRelationType;
	}

	public Property getWasCreatedOnRelationType() {
		return wasCreatedOnRelationType;
	}

	public Property getRelationType() {
		return relationType;
	}

	public Property getPropertyType() {
		return propertyType;
	}

	public Property getConceptType() {
		return conceptType;
	}

	public Property getIsAvailableToRelationType() {
		return isAvailableToRelationType;
	}

}