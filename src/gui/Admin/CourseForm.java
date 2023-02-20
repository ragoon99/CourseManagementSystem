package gui.Admin;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.Courses;
import backend.Details.CourseDetails;
import exception.ErrorGUI;
import gui.PopUpMessage;

public class CourseForm extends Courses {
	private String courseCode;
	private boolean update;

	public CourseForm() {
		this(null, false);
	}
	
	public CourseForm(String courseCode, boolean update) {
		this.courseCode = courseCode;
		this.update = update;
		
		new CourseFormGUI();
	}
	
	public class CourseFormGUI extends JDialog {
		private JPanel contentPane;
		
		private JTextField courseCode_field;
		private JTextField totalSem_field;
		private JTextField courseDuration_field;
		private JTextField courseName_field;
		private JTextField totalModule_field;
		
		private JButton addCourse_button;
		
		CourseDetails courseDetails = new CourseDetails();
		
		public CourseFormGUI() {
			if (update) {
				updateCourseForm();
			} else {
				showCourseGUI();
			}
		}
		
		private void showCourseGUI() {
			setTitle("Add Course");
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 600, 300);
			setLocationRelativeTo(null);
			setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			
			JPanel top_panel = new JPanel();
			contentPane.add(top_panel, BorderLayout.NORTH);
			top_panel.setLayout(new BorderLayout(0, 0));
			
			JLabel title = new JLabel("Add Course");
			title.setFont(new Font("Poppins", Font.BOLD, 26));
			title.setHorizontalAlignment(SwingConstants.CENTER);
			top_panel.add(title);
			
			JPanel center_panel = new JPanel();
			contentPane.add(center_panel, BorderLayout.CENTER);
			GridBagLayout gbl_center_panel = new GridBagLayout();
			gbl_center_panel.columnWidths = new int[]{30, 0, 86, 0, 37, 97, 30, 0};
			gbl_center_panel.rowHeights = new int[]{30, 22, 30, 0, 30, 0, 0};
			gbl_center_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_center_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			center_panel.setLayout(gbl_center_panel);
			
			JLabel courseCode_label = new JLabel("Course Code :");
			GridBagConstraints gbc_courseCode_label = new GridBagConstraints();
			gbc_courseCode_label.anchor = GridBagConstraints.EAST;
			gbc_courseCode_label.insets = new Insets(0, 0, 5, 5);
			gbc_courseCode_label.gridx = 1;
			gbc_courseCode_label.gridy = 1;
			center_panel.add(courseCode_label, gbc_courseCode_label);
			
			courseCode_field = new JTextField();
			GridBagConstraints gbc_courseCode_field = new GridBagConstraints();
			gbc_courseCode_field.insets = new Insets(0, 0, 5, 5);
			gbc_courseCode_field.fill = GridBagConstraints.BOTH;
			gbc_courseCode_field.gridx = 2;
			gbc_courseCode_field.gridy = 1;
			center_panel.add(courseCode_field, gbc_courseCode_field);
			courseCode_field.setColumns(10);
			
			JLabel courseName_label = new JLabel("Course Name :");
			GridBagConstraints gbc_courseName_label = new GridBagConstraints();
			gbc_courseName_label.anchor = GridBagConstraints.EAST;
			gbc_courseName_label.insets = new Insets(0, 0, 5, 5);
			gbc_courseName_label.gridx = 3;
			gbc_courseName_label.gridy = 1;
			center_panel.add(courseName_label, gbc_courseName_label);
			
			courseName_field = new JTextField();
			GridBagConstraints gbc_courseName_field = new GridBagConstraints();
			gbc_courseName_field.insets = new Insets(0, 0, 5, 5);
			gbc_courseName_field.fill = GridBagConstraints.BOTH;
			gbc_courseName_field.gridx = 5;
			gbc_courseName_field.gridy = 1;
			center_panel.add(courseName_field, gbc_courseName_field);
			courseName_field.setColumns(10);
			
			JLabel totalSem_label = new JLabel("Total Semester :");
			GridBagConstraints gbc_totalSem_label = new GridBagConstraints();
			gbc_totalSem_label.anchor = GridBagConstraints.EAST;
			gbc_totalSem_label.insets = new Insets(0, 0, 5, 5);
			gbc_totalSem_label.gridx = 1;
			gbc_totalSem_label.gridy = 3;
			center_panel.add(totalSem_label, gbc_totalSem_label);
			
			totalSem_field = new JTextField();
			GridBagConstraints gbc_totalSem_field = new GridBagConstraints();
			gbc_totalSem_field.insets = new Insets(0, 0, 5, 5);
			gbc_totalSem_field.fill = GridBagConstraints.BOTH;
			gbc_totalSem_field.gridx = 2;
			gbc_totalSem_field.gridy = 3;
			center_panel.add(totalSem_field, gbc_totalSem_field);
			totalSem_field.setColumns(10);
			
			JLabel totalModule_label = new JLabel("Total Modules :");
			GridBagConstraints gbc_totalModule_label = new GridBagConstraints();
			gbc_totalModule_label.anchor = GridBagConstraints.EAST;
			gbc_totalModule_label.insets = new Insets(0, 0, 5, 5);
			gbc_totalModule_label.gridx = 3;
			gbc_totalModule_label.gridy = 3;
			center_panel.add(totalModule_label, gbc_totalModule_label);
			
			totalModule_field = new JTextField();
			GridBagConstraints gbc_totalModule_field = new GridBagConstraints();
			gbc_totalModule_field.insets = new Insets(0, 0, 5, 5);
			gbc_totalModule_field.fill = GridBagConstraints.BOTH;
			gbc_totalModule_field.gridx = 5;
			gbc_totalModule_field.gridy = 3;
			center_panel.add(totalModule_field, gbc_totalModule_field);
			totalModule_field.setColumns(10);
			
			JLabel courseDuration_label = new JLabel("Course Duration (Months) :");
			GridBagConstraints gbc_courseDuration_label = new GridBagConstraints();
			gbc_courseDuration_label.anchor = GridBagConstraints.EAST;
			gbc_courseDuration_label.insets = new Insets(0, 0, 0, 5);
			gbc_courseDuration_label.gridx = 1;
			gbc_courseDuration_label.gridy = 5;
			center_panel.add(courseDuration_label, gbc_courseDuration_label);
			
			courseDuration_field = new JTextField();
			GridBagConstraints gbc_courseDuration_field = new GridBagConstraints();
			gbc_courseDuration_field.insets = new Insets(0, 0, 0, 5);
			gbc_courseDuration_field.fill = GridBagConstraints.BOTH;
			gbc_courseDuration_field.gridx = 2;
			gbc_courseDuration_field.gridy = 5;
			center_panel.add(courseDuration_field, gbc_courseDuration_field);
			courseDuration_field.setColumns(10);
			
			JLabel availablility_label = new JLabel("Available : ");
			GridBagConstraints gbc_avalibility_field = new GridBagConstraints();
			gbc_avalibility_field.anchor = GridBagConstraints.EAST;
			gbc_avalibility_field.insets = new Insets(0, 0, 0, 5);
			gbc_avalibility_field.gridx = 3;
			gbc_avalibility_field.gridy = 5;
			center_panel.add(availablility_label, gbc_avalibility_field);
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("");
			GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
			gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
			gbc_chckbxNewCheckBox.gridx = 5;
			gbc_chckbxNewCheckBox.gridy = 5;
			center_panel.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
			
			JPanel bottom_panel = new JPanel();
			contentPane.add(bottom_panel, BorderLayout.SOUTH);
			bottom_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			addCourse_button = new JButton("Add Course");
			addCourse_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						courseDetails.setCourseCode(courseCode_field.getText());
						courseDetails.setCourseName(courseName_field.getText());
						courseDetails.setTotalSemester(Integer.parseInt(totalSem_field.getText()));
						courseDetails.setTotalModule(Integer.parseInt(totalModule_field.getText()));
						courseDetails.setCourseLength(Integer.parseInt(courseDuration_field.getText()));
						courseDetails.setAvailibility(chckbxNewCheckBox.isSelected());
						
						boolean execute = createCourse(courseDetails);
							
						if (execute) {
							new PopUpMessage("Added Course Successfully");
							dispose();
						} else {
							new ErrorGUI("Error Occured While Adding Course");
						}
						
					} catch (Exception e2) {
						if (e2 instanceof NumberFormatException) {
							new ErrorGUI("Semester, Total Module & Course Length Field Show Be Digits");
						}
					}
					
				}
			});
			
			emptyBoxChecker();
			
			bottom_panel.add(addCourse_button);
			
			setVisible(true);
		}
		
		private void updateCourseForm() {
			setTitle("Add Course");
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 600, 300);
			setLocationRelativeTo(null);
			setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			
			JPanel top_panel = new JPanel();
			contentPane.add(top_panel, BorderLayout.NORTH);
			top_panel.setLayout(new BorderLayout(0, 0));
			
			JLabel title = new JLabel("Add Course");
			title.setFont(new Font("Poppins", Font.BOLD, 26));
			title.setHorizontalAlignment(SwingConstants.CENTER);
			top_panel.add(title);
			
			JPanel center_panel = new JPanel();
			contentPane.add(center_panel, BorderLayout.CENTER);
			GridBagLayout gbl_center_panel = new GridBagLayout();
			gbl_center_panel.columnWidths = new int[]{30, 0, 86, 0, 37, 97, 30, 0};
			gbl_center_panel.rowHeights = new int[]{30, 22, 30, 0, 30, 0, 0};
			gbl_center_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_center_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			center_panel.setLayout(gbl_center_panel);
			
			JLabel courseCode_label = new JLabel("Course Code :");
			GridBagConstraints gbc_courseCode_label = new GridBagConstraints();
			gbc_courseCode_label.anchor = GridBagConstraints.EAST;
			gbc_courseCode_label.insets = new Insets(0, 0, 5, 5);
			gbc_courseCode_label.gridx = 1;
			gbc_courseCode_label.gridy = 1;
			center_panel.add(courseCode_label, gbc_courseCode_label);
			
			courseCode_field = new JTextField();
			GridBagConstraints gbc_courseCode_field = new GridBagConstraints();
			gbc_courseCode_field.insets = new Insets(0, 0, 5, 5);
			gbc_courseCode_field.fill = GridBagConstraints.BOTH;
			gbc_courseCode_field.gridx = 2;
			gbc_courseCode_field.gridy = 1;
			center_panel.add(courseCode_field, gbc_courseCode_field);
			courseCode_field.setColumns(10);
			
			JLabel courseName_label = new JLabel("Course Name :");
			GridBagConstraints gbc_courseName_label = new GridBagConstraints();
			gbc_courseName_label.anchor = GridBagConstraints.EAST;
			gbc_courseName_label.insets = new Insets(0, 0, 5, 5);
			gbc_courseName_label.gridx = 3;
			gbc_courseName_label.gridy = 1;
			center_panel.add(courseName_label, gbc_courseName_label);
			
			courseName_field = new JTextField();
			GridBagConstraints gbc_courseName_field = new GridBagConstraints();
			gbc_courseName_field.insets = new Insets(0, 0, 5, 5);
			gbc_courseName_field.fill = GridBagConstraints.BOTH;
			gbc_courseName_field.gridx = 5;
			gbc_courseName_field.gridy = 1;
			center_panel.add(courseName_field, gbc_courseName_field);
			courseName_field.setColumns(10);
			
			JLabel totalSem_label = new JLabel("Total Semester :");
			GridBagConstraints gbc_totalSem_label = new GridBagConstraints();
			gbc_totalSem_label.anchor = GridBagConstraints.EAST;
			gbc_totalSem_label.insets = new Insets(0, 0, 5, 5);
			gbc_totalSem_label.gridx = 1;
			gbc_totalSem_label.gridy = 3;
			center_panel.add(totalSem_label, gbc_totalSem_label);
			
			totalSem_field = new JTextField();
			GridBagConstraints gbc_totalSem_field = new GridBagConstraints();
			gbc_totalSem_field.insets = new Insets(0, 0, 5, 5);
			gbc_totalSem_field.fill = GridBagConstraints.BOTH;
			gbc_totalSem_field.gridx = 2;
			gbc_totalSem_field.gridy = 3;
			center_panel.add(totalSem_field, gbc_totalSem_field);
			totalSem_field.setColumns(10);
			
			JLabel totalModule_label = new JLabel("Total Modules :");
			GridBagConstraints gbc_totalModule_label = new GridBagConstraints();
			gbc_totalModule_label.anchor = GridBagConstraints.EAST;
			gbc_totalModule_label.insets = new Insets(0, 0, 5, 5);
			gbc_totalModule_label.gridx = 3;
			gbc_totalModule_label.gridy = 3;
			center_panel.add(totalModule_label, gbc_totalModule_label);
			
			totalModule_field = new JTextField();
			GridBagConstraints gbc_totalModule_field = new GridBagConstraints();
			gbc_totalModule_field.insets = new Insets(0, 0, 5, 5);
			gbc_totalModule_field.fill = GridBagConstraints.BOTH;
			gbc_totalModule_field.gridx = 5;
			gbc_totalModule_field.gridy = 3;
			center_panel.add(totalModule_field, gbc_totalModule_field);
			totalModule_field.setColumns(10);
			
			JLabel courseDuration_label = new JLabel("Course Duration (Months) :");
			GridBagConstraints gbc_courseDuration_label = new GridBagConstraints();
			gbc_courseDuration_label.anchor = GridBagConstraints.EAST;
			gbc_courseDuration_label.insets = new Insets(0, 0, 0, 5);
			gbc_courseDuration_label.gridx = 1;
			gbc_courseDuration_label.gridy = 5;
			center_panel.add(courseDuration_label, gbc_courseDuration_label);
			
			courseDuration_field = new JTextField();
			GridBagConstraints gbc_courseDuration_field = new GridBagConstraints();
			gbc_courseDuration_field.insets = new Insets(0, 0, 0, 5);
			gbc_courseDuration_field.fill = GridBagConstraints.BOTH;
			gbc_courseDuration_field.gridx = 2;
			gbc_courseDuration_field.gridy = 5;
			center_panel.add(courseDuration_field, gbc_courseDuration_field);
			courseDuration_field.setColumns(10);
			
			JLabel avalibility_field = new JLabel("Available : ");
			GridBagConstraints gbc_avalibility_field = new GridBagConstraints();
			gbc_avalibility_field.anchor = GridBagConstraints.EAST;
			gbc_avalibility_field.insets = new Insets(0, 0, 0, 5);
			gbc_avalibility_field.gridx = 3;
			gbc_avalibility_field.gridy = 5;
			center_panel.add(avalibility_field, gbc_avalibility_field);
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("");
			GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
			gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
			gbc_chckbxNewCheckBox.gridx = 5;
			gbc_chckbxNewCheckBox.gridy = 5;
			center_panel.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
			
			JPanel bottom_panel = new JPanel();
			contentPane.add(bottom_panel, BorderLayout.SOUTH);
			bottom_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			addCourse_button = new JButton("Add Course");
			addCourse_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						courseDetails.setCourseName(courseName_field.getText());
						courseDetails.setTotalSemester(Integer.parseInt(totalSem_field.getText()));
						courseDetails.setTotalModule(Integer.parseInt(totalModule_field.getText()));
						courseDetails.setCourseLength(Integer.parseInt(courseDuration_field.getText()));
						
						boolean execute = updateCourse(courseDetails, courseCode_field.getText());
						
						if (execute) {
							new PopUpMessage("Updated Course Successfully");
							dispose();
						} else {
							new ErrorGUI("Error Occured While Updating Course");
						}
					} catch (Exception e2) {
						if (e2 instanceof NumberFormatException) {
							new ErrorGUI("Semester, Total Module & Course Length Field Show Be Digits");
						} else if (e2 instanceof NullPointerException) {
							new ErrorGUI("All Fields Are Mandatory");
						}
					}
					
				}
			});
			bottom_panel.add(addCourse_button);
			
			addCourse_button.setText("Update");
			addCourse_button.setEnabled(false);
			title.setText("Update Course");
			
			courseDetails = getCourseDetail(courseCode);
			
			courseCode_field.setText(courseDetails.getCourseCode());
			courseName_field.setText(courseDetails.getCourseName());
			totalSem_field.setText(String.valueOf(courseDetails.getTotalSemester()));
			totalModule_field.setText(String.valueOf(courseDetails.getTotalModule()));
			courseDuration_field.setText(String.valueOf(courseDetails.getCourseLength()));
			
			if (courseDetails.getAvailibility() == "Available") {
				chckbxNewCheckBox.setSelected(true);
			} else {
				chckbxNewCheckBox.setSelected(false);
			}
			
			emptyBoxChecker();
			
			setVisible(true);
		}
		
		private void emptyBoxChecker() {
			KeyboardFocusManager.getCurrentKeyboardFocusManager()
			  .addKeyEventDispatcher((KeyEventDispatcher) new KeyEventDispatcher() {
			      @Override
			      public boolean dispatchKeyEvent(KeyEvent e) {
			    	  if (courseCode_field.getText().isEmpty() || totalSem_field.getText().isEmpty() || 
			    			  courseDuration_field.getText().isEmpty() || courseName_field.getText().isEmpty() || 
			    			  totalModule_field.getText().isEmpty()) {
			    		  	addCourse_button.setEnabled(false);
			    	  } else {
			    		  	addCourse_button.setEnabled(true);
			    	  }
			    	  
			    	  return false;
			      }
			  });
		}
	}
}
