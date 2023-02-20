package exception;

public class StudentModuleCountLimitException extends Throwable {
	public StudentModuleCountLimitException() {
		super("You Have Already Enrolled in 4 Modules");
	}
}
