package exception;

public class DuplicateTutorModuleRegistrationExecption extends Throwable {
	public DuplicateTutorModuleRegistrationExecption() {
		super("Tutor is Already Registered in the Specified Module");
	}
}
