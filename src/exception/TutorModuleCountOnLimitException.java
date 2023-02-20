package exception;

public class TutorModuleCountOnLimitException extends Throwable {
	public TutorModuleCountOnLimitException() {
		super("Tutor Can't be assigned to more than 4 Module at time.");
	}
}
