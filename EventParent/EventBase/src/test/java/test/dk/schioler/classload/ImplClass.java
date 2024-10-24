package test.dk.schioler.classload;

public class ImplClass extends MiddleClass implements ImplIF{
	static {
		System.err.println("ImplClass before static");
	}

	protected static String IC = "ImplClass";

	static {
		System.err.println("ImplClass, after static");
	}

	public ImplClass() {
		super();
		System.err.println("ImplClass, Coonstructor");
	}
	
	
	public static String getTable() {
		return TABLE;
	}

}
