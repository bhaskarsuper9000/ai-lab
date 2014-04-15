class Debug{
	public static final boolean DEBUG_ON = true;
	public static final boolean DEBUG_OFF = false;
	
	private static boolean debugMode = DEBUG_ON;	//default on
	
	public static void setDebugMode( boolean b ){
		debugMode = b;
	}
	
	public static void print(String s){
		if(debugMode)
			System.out.print("[DEBUG] "+s);
	}
	
	public static void println(String s){
		if(debugMode)
			System.out.println("[DEBUG] "+s);
	}
	
}
