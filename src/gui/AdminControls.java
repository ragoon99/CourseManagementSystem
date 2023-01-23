package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminControls extends JPanel {

	/**
	 * Create the panel.
	 */
	public AdminControls() {
		setLayout(new BorderLayout(0, 0));
		setVisible(true);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][173.00][44.00][176.00]", "[][54.00]"));
		
		JButton addTutor_button = new JButton("Add Tutor");
		addTutor_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TutorForm();
			}
		});
		panel.add(addTutor_button, "cell 1 1,grow");
		
		JButton addStudent_button = new JButton("Add Student");
		panel.add(addStudent_button, "cell 3 1,grow");
		
	}

}
