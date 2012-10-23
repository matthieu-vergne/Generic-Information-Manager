package fr.vergne.util;

import java.util.Iterator;
import java.util.Set;

public class ClassSearcherIterator<T> implements Iterator<T> {
	private final FilteredSetIterator<Object> iterator;

	@SuppressWarnings("unchecked")
	public ClassSearcherIterator(Set<?> set, final Class<T> elementClass) {
		iterator = new FilteredSetIterator<Object>((Set<Object>) set) {
			@Override
			protected boolean isConsidered(Object element) {
				return elementClass.isInstance(element);
			}
		};
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next() {
		return (T) iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}

}
