package backend;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains the validation methods for user input
 * 
 */
public class Validation {
	/**
	 * This method checks if the Email is valid or not using the regex
	 * 
	 * @param String email to check
	 * 
	 * @return returns true if email is valid
	 * 			false if email is not valid
	 */
	public boolean checkEmail(String email) {
		Pattern p = Pattern.compile("[a-zA-Z0-9]++@[a-zA-Z]++.[a-zA-Z]++");
		Matcher m = p.matcher(email);
		
		return m.find();
	}
	
	/**
	 * This method checks if the password is valid or not using the regex
	 * 
	 * @param String password to check
	 * 
	 * @return returns true if password is valid
	 * 			false if password is not valid
	 */
	public boolean checkPassword(String password) {
		return checkPassword(password, null);
	}
	
	public boolean checkPassword(String password, String confirmPassword) {
		Pattern p = Pattern.compile("\\p{Punct}", Pattern.UNICODE_CHARACTER_CLASS);
		Matcher m = p.matcher(password);
		
		if (confirmPassword == null) {
			boolean passValid = (password.length() > 5) && m.find();
			
			return passValid;
		}
		
		boolean passValid = password.equals(confirmPassword) && (password.length() > 5) && m.find();
		
		return passValid;
	}
	
	/**
	 * This method is used to get the age of the user
	 * 
	 * @param String dob to get age from
	 * 
	 * @return returns int age 
	 */
	int getAge(String dob) {
		Calendar calendar = Calendar.getInstance();
		
		String[] usrYear = dob.split("/|-");
		
		int year = Integer.parseInt(usrYear[0]);
		int date = calendar.get(Calendar.YEAR);

		return date-year;
	}
	
}