import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import exception.ErrorGUI;
import gui.LoginSignup;

public class Main {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					new LoginSignup();
				} catch (Exception e) {
					new ErrorGUI("Failed To Load The Application.", e);
				}
			}
		});
	}
}
