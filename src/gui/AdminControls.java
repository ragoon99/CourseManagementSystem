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
		panel.setLayout(new MigLayout("", "[40][150][40][150][40][150]", "[][55][36.00][55][36][55]"));
		
		JButton addTutor_button = new JButton("Add Tutor");
		addTutor_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TutorForm();
			}
		});
		panel.add(addTutor_button, "cell 1 1,grow");
		
		JButton addStudent_button = new JButton("Add Student");
		panel.add(addStudent_button, "cell 3 1,grow");
		
		JButton addCourse_button = new JButton("Add Course");
		panel.add(addCourse_button, "cell 5 1,grow");
		
		JButton removeTutor_button = new JButton("Remove Tutor");
		panel.add(removeTutor_button, "cell 1 3,grow");
		
		JButton removeStudent_button = new JButton("Remove Student");
		panel.add(removeStudent_button, "cell 3 3,grow");
		
		JButton removeCourse_button = new JButton("Remove Course");
		panel.add(removeCourse_button, "cell 5 3,grow");
		
		JButton cancelCourse_button = new JButton("Cancel Course");
		cancelCourse_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(cancelCourse_button, "cell 5 5,grow");
		
	}

}
