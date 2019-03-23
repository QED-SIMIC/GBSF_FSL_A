package vm;


import bo.User;
import util.ExceptionsClerk;

public class Instruction {
	
	public enum InstructionCode {
		INITALIZE_SESSION, SIGN_IN, SET_TIME_BETWEEN_INSTRUCTIONS
		
	};
	
	
	protected User user;
	protected InstructionCode instructionCode;
	protected String sessionName;
	protected int seconds;

	
	//-----------------------------------------------------
		protected Instruction() {
			this.user = null;
			this.instructionCode = null;
			this.sessionName = null;
			this.seconds = -1;
		}
		
		//----------------------------------------------------------
				public String toString() {
					StringBuffer sb = new StringBuffer();
					
					sb.append("(");
					sb.append(this.instructionCode.name());
					
					switch(this.instructionCode) {
					
					case INITALIZE_SESSION:
						sb.append(", ");
						sb.append(sessionName);
						break;
						
					case SIGN_IN:
						sb.append(", ");
						sb.append(user.toString());
						break;
					
					case SET_TIME_BETWEEN_INSTRUCTIONS:
						sb.append(", ");
						sb.append(seconds);
						break;
				default:
						ExceptionsClerk.raiseAssertionError
						("Unsupported Instruction Admin code" + this.instructionCode.name());
					}
					sb.append(")");
					
					return sb.toString();
				}

}

