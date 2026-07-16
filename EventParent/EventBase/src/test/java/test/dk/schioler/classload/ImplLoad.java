package test.dk.schioler.classload;

public class ImplLoad extends MiddleClass implements ImplIF{
	static {
		System.err.println("ImplClass before static");
	}

	protected static String IC = "ImplClass";

	static {
		System.err.println("ImplClass, after static");
	}

	public ImplLoad() {
		super();
		System.err.println("ImplClass, Coonstructor");
	}
	
	
	public static String getTable() {
		return TABLE;
	}

}
