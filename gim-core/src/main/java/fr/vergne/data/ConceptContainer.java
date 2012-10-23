package fr.vergne.data;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class ConceptContainer {

	private final Set<Concept> concepts = new TreeSet<Concept>(
			new Comparator<Concept>() {
				@Override
				public int compare(Concept c1, Concept c2) {
					int comparison = c1.toString().compareTo(c2.toString());
					if (comparison == 0 && c1 != c2) {
						comparison = 1;
					} else if (comparison != 0 && c1 == c2) {
						throw new IllegalStateException(
								"This case should not happen");
					} else {
						// the computation corresponds
					}
					return comparison;
				}
			});
	private final Property system;
	private final Property owner;
	private final ConceptFactory systemFactory;
	private final ConceptFactory ownerFactory;

	public ConceptContainer() {
		system = new Property();
		addConcept(system);
		systemFactory = new ConceptFactory(this, system);
		systemFactory.setDecorationsActivated(false);
		systemFactory.makeCreatorRelation(system, system);
		systemFactory.makeCreationDateRelation(system, new Date());
		systemFactory.setDecorationsActivated(true);

		owner = systemFactory.makeProperty("You");
		ownerFactory = new ConceptFactory(this, owner);
		addConcept(owner);
		addConcept(systemFactory.makeProperty("Owner"));
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

	public Property getSystemConcept() {
		return system;
	}

	public Property getOwnerConcept() {
		return owner;
	}

	public ConceptFactory getSystemConceptFactory() {
		return systemFactory;
	}

	public ConceptFactory getOwnerConceptFactory() {
		return ownerFactory;
	}

}
