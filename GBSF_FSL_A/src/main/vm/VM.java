package vm;


import java.util.List;

import bo.User;
import env.Environment;
import page.SignInPage;
import util.ExceptionsClerk;
import util.TimeUtil;
import util.WebDriverUtil;

public class VM {
	
	private VM vm;
	private int currentInstructionNum;
	private int waitSecondsBetweenInstructions;
	private Environment env;

	//---------------------------------------------------------------
		public VM () {
			this.env = Environment.getInstance();
			this.currentInstructionNum = 0;
			this.waitSecondsBetweenInstructions = 0;
		
		}
		
		//---------------------------------------------------------------
		public void execute (Program program) {
			
			List<Instruction> instructions;
			Instruction instruction;
			
			
			instructions = program.getInstructions();
					
			for (Instruction i : instructions) {
				this.currentInstructionNum = this.currentInstructionNum +1;
				this.executeInstruction(i);
///				this.vm.executeWaitBetweenInstructions();
			}
		}
		
		//----------------------------------------------------------------
		private void executeInstruction(Instruction i) {
			switch (i.instructionCode) {
			
			case INITALIZE_SESSION:
				this.executeInitializeSession(i);
				break;
			case SIGN_IN:
				this.executeSignIn(i);
				break;
			
			
			default:
				ExceptionsClerk.raiseAssertionError("Unsupported Instruction Code: + instruction.toString(");
			}
			
		}
		
		// ---------------------------------------------------------------
		private void executeSignIn(Instruction i) {

			WebDriverUtil wdu = new WebDriverUtil();
			SignInPage signInPage = new SignInPage();
			User user = i.user;
			///HomePage homePage = new HomePage();
			boolean landedOnHomePage = false;

			user = this.getUserCredentials(user);
			wdu.loadUrl(this.env.getSignInUrl());
			signInPage.signInAs(user.getUserName(), user.getPassword());
			///wdu.waitForUrlChange(500);
			
			///landedOnHomePage = homePage.isHomePage();
	
			TimeUtil.waitSeconds(5);
			
		}
		
		// ---------------------------------------------------------------
		private User getUserCredentials(User user) {
			User userLocal;
			String userName;
			String password;
			
			userName = System.getenv("KO_SF");
			password = System.getenv("LZ_SF");
			
			userLocal = new User(userName, password);
		
			return userLocal;
		}

		// ---------------------------------------------------------------
		private void executeInitializeSession(Instruction i) {
			this.env.initiateSession(i.sessionName);
		}

		//------------------------------------------------------------
		private void executeSetTimeBetweenInstructions(Instruction instruction) {
			this.waitSecondsBetweenInstructions = instruction.seconds;
			
		}
		
		
		//---------------------------------------------------------------
				protected void executeWaitBetweenInstructions() {
					TimeUtil.waitSeconds(this.waitSecondsBetweenInstructions);
				}

}
