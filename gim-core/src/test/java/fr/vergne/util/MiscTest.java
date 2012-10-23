package fr.vergne.util;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.vergne.util.Misc;

public class MiscTest {

	@Test
	public void testGetMethodRecursivityDepth() {
		assertEquals(0, Misc.getMethodRecursivityDepth());
		assertEquals(0, recursiveFunction(0));
		assertEquals(1, recursiveFunction(1));
		assertEquals(2, recursiveFunction(2));
	}

	private int recursiveFunction(int remainingCalls) {
		if (remainingCalls > 0) {
			return recursiveFunction(remainingCalls - 1);
		} else {
			return Misc.getMethodRecursivityDepth();
		}
	}

}
