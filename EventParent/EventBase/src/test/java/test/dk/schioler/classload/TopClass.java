package test.dk.schioler.classload;

public class TopClass {
	
	static {
		System.err.println("TopClass before static");
	}

	
	protected static String TC = "TopClass";

	static {
		System.err.println("TopClass, after static");
	}

	public TopClass() {
		super();
		System.err.println("TopClass, Coonstructor");
	}

}
