package fr.vergne.util;

public class Misc {

	/**
	 * This method indicates the depth of recursivity reached in the method
	 * calls for the current method. It is especially useful to know if a method
	 * is in a potential infinite loop because it calls a method which calls its
	 * caller. A result of 0 means it is the first call (no recursivity), 1
	 * means this is the first recursivity (2 call in the stack trace), etc.
	 * 
	 * @return the recursivity depth (0 = first call, no recursivity)
	 */
	public static int getMethodRecursivityDepth() {
		StackTraceElement[] trace = new Exception().getStackTrace();
		StackTraceElement traceElement = trace[1];
		String refClass = traceElement.getClassName();
		String refMethod = traceElement.getMethodName();
		int depth = 0;
		for (int i = 2; i < trace.length; i++) {
			traceElement = trace[i];
			String sClass = traceElement.getClassName();
			String sMethod = traceElement.getMethodName();
			if (sClass.equals(refClass) && sMethod.equals(refMethod)) {
				depth++;
			} else {
				continue;
			}
		}
		return depth;
	}
}
