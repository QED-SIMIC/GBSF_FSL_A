package sandboxa;


import bo.User;
import vm.Program;

public class DemoA_Programs {
	private final static String FSL_SESSION ="FSL_Session";
	private final static String FSL_AGENT = "John West";
	
	/**
	 * Constructor
	 */
	public DemoA_Programs() {
		
	}

	 // --------------------------------------------------------------------
	public Program getSignInOnly() {
		Program p = new Program();
		DemoA_Data data = new DemoA_Data();
		User user = data.getAdminUser();
		
		
		p.initializeSession(FSL_SESSION);
		p.signIn(user);
  ///	p.impersonate (FSL_AGENT);
		return p;
		
	}

}