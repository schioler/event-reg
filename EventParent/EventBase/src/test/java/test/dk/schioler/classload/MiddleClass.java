package test.dk.schioler.classload;

public class MiddleClass extends TopClass {

	static {
		System.err.println("MiddleClass before static");
	}

	protected static String MC = "MiddleClass";

	static {
		System.err.println("MiddleClass, after static");
	}

	public MiddleClass() {
		super();
		System.err.println("MiddleClass, Coonstructor");
	}

}
