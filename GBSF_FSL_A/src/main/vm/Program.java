package vm;


import java.util.ArrayList;
import java.util.List;

import bo.User;

public class Program {

	private List<Instruction> instructions;

	// ---------------------------------------------------------------
	public Program() {
		this.instructions = new ArrayList<Instruction>();
	}

	// ---------------------------------------------------------------
	// ADMIN
	// ---------------------------------------------------------------
	public void initializeSession(String sessionName) {
		Instruction i = new Instruction();
		i.instructionCode = Instruction.InstructionCode.INITALIZE_SESSION;

		i.sessionName = sessionName;

		this.instructions.add(i);
	}

	// ---------------------------------------------------------------
	public void signIn(User user) {
		Instruction i = new Instruction();
		i.instructionCode = Instruction.InstructionCode.SIGN_IN;
		i.user = user;

		this.instructions.add(i);
	}

	// ---------------------------------------------------------------
	protected List<Instruction> getInstructions() {
		return this.instructions;
	}

}