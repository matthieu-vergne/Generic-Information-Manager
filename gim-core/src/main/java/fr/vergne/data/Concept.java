package fr.vergne.data;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import fr.vergne.data.property.DateProperty;
import fr.vergne.data.relation.IsCreatedOnRelation;
import fr.vergne.data.relation.IsRepresentedByRelation;
import fr.vergne.data.relation.Relation;
import fr.vergne.util.ClassSearcherIterator;
import fr.vergne.util.Misc;

public class Concept {

	private final Set<Relation<?, ?>> relations = new LinkedHashSet<Relation<?, ?>>();
	static private final Set<Concept> concepts = new LinkedHashSet<Concept>();

	public Concept() {
		concepts.add(this);
		if (this instanceof IsCreatedOnRelation) {
			// do not create such relation (infinite loop)
		} else {
			addRelation(new IsCreatedOnRelation(this, new DateProperty()));
		}
	}

	public static Set<Concept> getAllConcepts() {
		return concepts;
	}

	public static <T extends Concept> Set<T> getConcepts(Class<T> conceptClass) {
		Set<T> concepts = new HashSet<T>();
		ClassSearcherIterator<T> iterator = new ClassSearcherIterator<T>(
				getAllConcepts(), conceptClass);
		while (iterator.hasNext()) {
			concepts.add(iterator.next());
		}
		return concepts;
	}

	public void addRelation(Relation<?, ?> relation) {
		if (relation.getStart() == this || relation.getEnd() == this) {
			relations.add(relation);
		} else {
			throw new RuntimeException(
					"The relation is not related to this concept.");
		}
	}

	public Set<Relation<?, ?>> getRelations() {
		return relations;
	}

	public String getRepresentation() {
		ClassSearcherIterator<IsRepresentedByRelation> iterator = new ClassSearcherIterator<IsRepresentedByRelation>(
				getRelations(), IsRepresentedByRelation.class);
		IsRepresentedByRelation representation = iterator.next();
		if (representation != null) {
			return representation.getEnd().get();
		} else if (Misc.getMethodRecursivityDepth() > 0) {
			return "";
		} else {
			return toString();
		}
	}
}
