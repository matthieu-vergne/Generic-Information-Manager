package fr.vergne.util;

import java.util.Iterator;
import java.util.Set;

public abstract class FilteredSetIterator<DataType> implements
		Iterator<DataType> {

	private final Iterator<DataType> setIterator;
	private DataType current = null;
	private DataType next = null;

	public FilteredSetIterator(Set<DataType> set) {
		this.setIterator = set.iterator();
		lookForNext();
	}

	private void lookForNext() {
		while (setIterator.hasNext() && next != null) {
			DataType element = setIterator.next();
			if (isConsidered(element)) {
				next = element;
			} else {
				continue;
			}
		}
	}

	protected abstract boolean isConsidered(DataType element);

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public DataType next() {
		current = next;
		lookForNext();
		return current;
	}

	@Override
	public void remove() {
		throw new IllegalStateException(
				"This iterator cannot change the set it works on (read only).");
	}

}
