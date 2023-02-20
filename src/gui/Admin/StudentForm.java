package gui.Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import backend.Courses;
import backend.Students;
import backend.Validation;
import backend.Details.StudentDetails;
import exception.ErrorGUI;
import gui.PopUpMessage;
import utils.JTextFieldLimit;
import utils.LoadImage;

public class StudentForm extends Students {

	private JPanel contentPane;
	
	private JTextField email_field;
	private JTextField fname_field;
	private JTextField pass_field;
	private JTextField lname_field;
	private JTextField cPass_field;
	private Validation validator = new Validation();
	private StudentDetails studentDetails;
	
	private int id;
	private boolean update;
	
	public StudentForm() {
		this(0, false);
	}

	public StudentForm(int id, boolean update) {
		this.id = id;
		this.update = update;
		
		new StudentFormGUI();
	}
	
	private class StudentFormGUI extends JDialog {
		public StudentFormGUI() {
			if(update) {
				updateStudentFormGUI();
			} else {
				addStudentFormGUI();
			}
		}
		
		private void addStudentFormGUI() {
			setTitle("Add Student");
			setBackground(new Color(255, 255, 255));
			setResizable(false);
			setSize(new Dimension(471, 476));
			setPreferredSize(new Dimension(500, 600));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			contentPane = new JPanel();
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			
			JPanel topPanel = new JPanel();
			topPanel.setBackground(new Color(255, 255, 255));
			topPanel.setPreferredSize(new Dimension(10, 100));
			topPanel.setSize(new Dimension(0, 100));
			contentPane.add(topPanel, BorderLayout.NORTH);
			topPanel.setLayout(new BorderLayout(0, 0));
			
			JLabel title_label = new JLabel("Add Student");
			title_label.setFont(new Font("Poppins SemiBold", Font.PLAIN, 26));
			title_label.setHorizontalTextPosition(SwingConstants.CENTER);
			title_label.setHorizontalAlignment(SwingConstants.CENTER);
			topPanel.add(title_label);
			
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(new Color(255, 255, 255));
			bottomPanel.setPreferredSize(new Dimension(10, 100));
			bottomPanel.setSize(new Dimension(0, 100));
			contentPane.add(bottomPanel, BorderLayout.SOUTH);
			GridBagLayout gbl_bottomPanel = new GridBagLayout();
			gbl_bottomPanel.columnWidths = new int[]{85, 0};
			gbl_bottomPanel.rowHeights = new int[]{36, 0};
			gbl_bottomPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_bottomPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			bottomPanel.setLayout(gbl_bottomPanel);
			
			JPanel centerPanel = new JPanel();
			centerPanel.setBackground(new Color(255, 255, 255));
			contentPane.add(centerPanel);
			GridBagLayout gbl_centerPanel = new GridBagLayout();
			gbl_centerPanel.columnWidths = new int[]{67, 21, 33, 34, 0, 37, 0};
			gbl_centerPanel.rowHeights = new int[]{35, 25, 31, 25, 27, 25, 0};
			gbl_centerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			centerPanel.setLayout(gbl_centerPanel);
			
			JLabel fname_label = new JLabel("First Name :");
			fname_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_fname_label = new GridBagConstraints();
			gbc_fname_label.anchor = GridBagConstraints.WEST;
			gbc_fname_label.insets = new Insets(0, 0, 5, 5);
			gbc_fname_label.gridx = 1;
			gbc_fname_label.gridy = 0;
			centerPanel.add(fname_label, gbc_fname_label);
			
			JLabel lname_label = new JLabel("Last Name :");
			lname_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_lname_label = new GridBagConstraints();
			gbc_lname_label.anchor = GridBagConstraints.WEST;
			gbc_lname_label.insets = new Insets(0, 0, 5, 5);
			gbc_lname_label.gridx = 4;
			gbc_lname_label.gridy = 0;
			centerPanel.add(lname_label, gbc_lname_label);
			
			JLabel fname_icon = new JLabel(new LoadImage("/resource/name-icon.png"));
			GridBagConstraints gbc_fname_icon = new GridBagConstraints();
			gbc_fname_icon.anchor = GridBagConstraints.NORTHEAST;
			gbc_fname_icon.insets = new Insets(0, 0, 5, 5);
			gbc_fname_icon.gridx = 0;
			gbc_fname_icon.gridy = 1;
			centerPanel.add(fname_icon, gbc_fname_icon);
			
			fname_field = new JTextField();
			fname_field.setToolTipText("First Name Should be less than 20 Character");
			fname_field.setDocument(new JTextFieldLimit(20));
			fname_field.setPreferredSize(new Dimension(7, 10));
			fname_field.setSize(new Dimension(0, 20));
			fname_field.setBorder(new LineBorder(new Color(192, 192, 192)));
			GridBagConstraints gbc_fname_field = new GridBagConstraints();
			gbc_fname_field.fill = GridBagConstraints.BOTH;
			gbc_fname_field.insets = new Insets(0, 0, 5, 5);
			gbc_fname_field.gridx = 1;
			gbc_fname_field.gridy = 1;
			centerPanel.add(fname_field, gbc_fname_field);
			fname_field.setColumns(10);
			
			lname_field = new JTextField();
			lname_field.setToolTipText("Last Name Should be less than 20 Character");
			lname_field.setDocument(new JTextFieldLimit(20));
			lname_field.setPreferredSize(new Dimension(7, 10));
			lname_field.setSize(new Dimension(0, 20));
			lname_field.setBorder(new LineBorder(new Color(192, 192, 192)));
			GridBagConstraints gbc_lname_field = new GridBagConstraints();
			gbc_lname_field.insets = new Insets(0, 0, 5, 5);
			gbc_lname_field.fill = GridBagConstraints.BOTH;
			gbc_lname_field.gridx = 4;
			gbc_lname_field.gridy = 1;
			centerPanel.add(lname_field, gbc_lname_field);
			lname_field.setColumns(10);
			
			JLabel pass_label = new JLabel("Password :");
			pass_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_pass_label = new GridBagConstraints();
			gbc_pass_label.anchor = GridBagConstraints.WEST;
			gbc_pass_label.insets = new Insets(0, 0, 5, 5);
			gbc_pass_label.gridx = 1;
			gbc_pass_label.gridy = 2;
			centerPanel.add(pass_label, gbc_pass_label);
			
			JLabel cPass_label = new JLabel("Confirm Password :");
			cPass_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_cPass_label = new GridBagConstraints();
			gbc_cPass_label.anchor = GridBagConstraints.WEST;
			gbc_cPass_label.insets = new Insets(0, 0, 5, 5);
			gbc_cPass_label.gridx = 4;
			gbc_cPass_label.gridy = 2;
			centerPanel.add(cPass_label, gbc_cPass_label);
			
			JLabel pass_icon = new JLabel(new LoadImage("/resource/password-icon.png"));
			GridBagConstraints gbc_pass_icon = new GridBagConstraints();
			gbc_pass_icon.anchor = GridBagConstraints.NORTHEAST;
			gbc_pass_icon.insets = new Insets(0, 0, 5, 5);
			gbc_pass_icon.gridx = 0;
			gbc_pass_icon.gridy = 3;
			centerPanel.add(pass_icon, gbc_pass_icon);
			
			pass_field = new JTextField();
			pass_field.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(pass_field.getText().length() < 5) {
						pass_field.setBorder(new LineBorder(Color.red));
					} else {
						pass_field.setBorder(new LineBorder(Color.gray, 1, false));
					}
				}
			});
			pass_field.setPreferredSize(new Dimension(7, 10));
			pass_field.setSize(new Dimension(0, 30));
			pass_field.setBorder(new LineBorder(new Color(192, 192, 192)));
			GridBagConstraints gbc_pass_field = new GridBagConstraints();
			gbc_pass_field.fill = GridBagConstraints.BOTH;
			gbc_pass_field.insets = new Insets(0, 0, 5, 5);
			gbc_pass_field.gridx = 1;
			gbc_pass_field.gridy = 3;
			centerPanel.add(pass_field, gbc_pass_field);
			pass_field.setColumns(10);
			
			cPass_field = new JTextField();
			cPass_field.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(cPass_field.getText().length() < 5) {
						cPass_field.setBorder(new LineBorder(Color.red));
					} else {
						cPass_field.setBorder(new LineBorder(Color.gray, 1, false));
					}
				}
			});
			cPass_field.setPreferredSize(new Dimension(7, 10));
			cPass_field.setSize(new Dimension(0, 20));
			cPass_field.setBorder(new LineBorder(new Color(192, 192, 192)));
			GridBagConstraints gbc_cPass_field = new GridBagConstraints();
			gbc_cPass_field.insets = new Insets(0, 0, 5, 5);
			gbc_cPass_field.fill = GridBagConstraints.BOTH;
			gbc_cPass_field.gridx = 4;
			gbc_cPass_field.gridy = 3;
			centerPanel.add(cPass_field, gbc_cPass_field);
			cPass_field.setColumns(10);
			
			JLabel course_label = new JLabel("Select Course :");
			course_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_course_label = new GridBagConstraints();
			gbc_course_label.anchor = GridBagConstraints.WEST;
			gbc_course_label.insets = new Insets(0, 0, 5, 5);
			gbc_course_label.gridx = 1;
			gbc_course_label.gridy = 4;
			centerPanel.add(course_label, gbc_course_label);
			
			JLabel email_label = new JLabel("Email :");
			email_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_email_label = new GridBagConstraints();
			gbc_email_label.anchor = GridBagConstraints.WEST;
			gbc_email_label.insets = new Insets(0, 0, 5, 5);
			gbc_email_label.gridx = 4;
			gbc_email_label.gridy = 4;
			centerPanel.add(email_label, gbc_email_label);
			
			JLabel course_icon = new JLabel(new LoadImage("/resource/course-icon.png"));
			GridBagConstraints gbc_course_icon = new GridBagConstraints();
			gbc_course_icon.anchor = GridBagConstraints.NORTHEAST;
			gbc_course_icon.insets = new Insets(0, 0, 5, 5);
			gbc_course_icon.gridx = 0;
			gbc_course_icon.gridy = 5;
			centerPanel.add(course_icon, gbc_course_icon);
			
			JComboBox<Object> course_combo = new JComboBox<Object>();
			course_combo.setModel(new DefaultComboBoxModel<>(new Courses().availableCourses().keySet().toArray()));
			GridBagConstraints gbc_course_combo = new GridBagConstraints();
			gbc_course_combo.insets = new Insets(0, 0, 5, 5);
			gbc_course_combo.fill = GridBagConstraints.BOTH;
			gbc_course_combo.gridx = 1;
			gbc_course_combo.gridy = 5;
			centerPanel.add(course_combo, gbc_course_combo);
			
			JLabel email_icon = new JLabel(new LoadImage("/resource/email-icon.png"));
			GridBagConstraints gbc_email_icon = new GridBagConstraints();
			gbc_email_icon.insets = new Insets(0, 0, 5, 5);
			gbc_email_icon.anchor = GridBagConstraints.NORTHEAST;
			gbc_email_icon.gridx = 3;
			gbc_email_icon.gridy = 5;
			centerPanel.add(email_icon, gbc_email_icon);
			
			email_field = new JTextField();
			GridBagConstraints gbc_email_field = new GridBagConstraints();
			gbc_email_field.insets = new Insets(0, 0, 5, 5);
			gbc_email_field.fill = GridBagConstraints.BOTH;
			gbc_email_field.gridx = 4;
			gbc_email_field.gridy = 5;
			centerPanel.add(email_field, gbc_email_field);
			email_field.setColumns(10);
			
			JLabel message = new JLabel("");
			GridBagConstraints gbc_message = new GridBagConstraints();
			gbc_message.gridwidth = 6;
			gbc_message.insets = new Insets(0, 0, 0, 5);
			gbc_message.gridx = 0;
			gbc_message.gridy = 6;
			centerPanel.add(message, gbc_message);
			
			email_field.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(validator.checkEmail(email_field.getText())) {
						message.setText("");
					} else {
						message.setText("Invalid Email");
					}
				}
			});
			
			JButton register_button = new JButton("Add Student");
			register_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String pass = pass_field.getText();
						String cPass = cPass_field.getText();
						
						if (!validator.checkPassword(pass, cPass)) {
							new ErrorGUI("Password Does Not Match");
							return;
						}
						
						studentDetails = new StudentDetails();
						
						studentDetails.setStdId(generateStudentID());
						studentDetails.setFirstName(fname_field.getText());
						studentDetails.setLastName(lname_field.getText());
						studentDetails.setCourseEnrolled((String) course_combo.getSelectedItem());
						studentDetails.setEmail(email_field.getText());
						studentDetails.setPassword(cPass);
						
						boolean execute = createStudent(studentDetails);
						
						if (execute) {
							new PopUpMessage("Successfully Created Account", "Student ID: " + studentDetails.getStdId(), "Password: " + studentDetails.getPassword());							
							dispose();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
						new ErrorGUI("All Fields are Mandatory.");
					}
				}
			});
			GridBagConstraints gbc_register_button = new GridBagConstraints();
			gbc_register_button.gridx = 0;
			gbc_register_button.gridy = 0;
			bottomPanel.add(register_button, gbc_register_button);
			
			setVisible(true);
		}
		
		private void updateStudentFormGUI() {
			setTitle("Update Student");
			setBackground(new Color(255, 255, 255));
			setResizable(false);
			setSize(new Dimension(471, 444));
			setPreferredSize(new Dimension(500, 600));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			contentPane = new JPanel();
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			
			JPanel topPanel = new JPanel();
			topPanel.setBackground(new Color(255, 255, 255));
			topPanel.setPreferredSize(new Dimension(10, 100));
			topPanel.setSize(new Dimension(0, 100));
			contentPane.add(topPanel, BorderLayout.NORTH);
			topPanel.setLayout(new BorderLayout(0, 0));
			
			JLabel title_label = new JLabel("Student Form");
			title_label.setFont(new Font("Poppins SemiBold", Font.PLAIN, 26));
			title_label.setHorizontalTextPosition(SwingConstants.CENTER);
			title_label.setHorizontalAlignment(SwingConstants.CENTER);
			topPanel.add(title_label);
			
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(new Color(255, 255, 255));
			bottomPanel.setPreferredSize(new Dimension(10, 100));
			bottomPanel.setSize(new Dimension(0, 100));
			contentPane.add(bottomPanel, BorderLayout.SOUTH);
			GridBagLayout gbl_bottomPanel = new GridBagLayout();
			gbl_bottomPanel.columnWidths = new int[]{85, 0};
			gbl_bottomPanel.rowHeights = new int[]{36, 0};
			gbl_bottomPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_bottomPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			bottomPanel.setLayout(gbl_bottomPanel);
			
			JPanel centerPanel = new JPanel();
			centerPanel.setBackground(new Color(255, 255, 255));
			contentPane.add(centerPanel);
			GridBagLayout gbl_centerPanel = new GridBagLayout();
			gbl_centerPanel.columnWidths = new int[]{67, 21, 33, 0, 37, 0};
			gbl_centerPanel.rowHeights = new int[]{35, 25, 31, 25, 30, 0};
			gbl_centerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			centerPanel.setLayout(gbl_centerPanel);
			
			JLabel fname_label = new JLabel("First Name :");
			fname_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_fname_label = new GridBagConstraints();
			gbc_fname_label.anchor = GridBagConstraints.WEST;
			gbc_fname_label.insets = new Insets(0, 0, 5, 5);
			gbc_fname_label.gridx = 1;
			gbc_fname_label.gridy = 0;
			centerPanel.add(fname_label, gbc_fname_label);
			
			JLabel lname_label = new JLabel("Last Name :");
			lname_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_lname_label = new GridBagConstraints();
			gbc_lname_label.anchor = GridBagConstraints.WEST;
			gbc_lname_label.insets = new Insets(0, 0, 5, 5);
			gbc_lname_label.gridx = 3;
			gbc_lname_label.gridy = 0;
			centerPanel.add(lname_label, gbc_lname_label);
			
			JLabel fname_icon = new JLabel(new LoadImage("/resource/name-icon.png"));
			GridBagConstraints gbc_fname_icon = new GridBagConstraints();
			gbc_fname_icon.anchor = GridBagConstraints.NORTHEAST;
			gbc_fname_icon.insets = new Insets(0, 0, 5, 5);
			gbc_fname_icon.gridx = 0;
			gbc_fname_icon.gridy = 1;
			centerPanel.add(fname_icon, gbc_fname_icon);
			
			fname_field = new JTextField();
			fname_field.setToolTipText("First Name Should be less than 20 Character");
			fname_field.setDocument(new JTextFieldLimit(20));
			fname_field.setPreferredSize(new Dimension(7, 10));
			fname_field.setSize(new Dimension(0, 20));
			fname_field.setBorder(new LineBorder(new Color(192, 192, 192)));
			GridBagConstraints gbc_fname_field = new GridBagConstraints();
			gbc_fname_field.fill = GridBagConstraints.BOTH;
			gbc_fname_field.insets = new Insets(0, 0, 5, 5);
			gbc_fname_field.gridx = 1;
			gbc_fname_field.gridy = 1;
			centerPanel.add(fname_field, gbc_fname_field);
			fname_field.setColumns(10);
			
			lname_field = new JTextField();
			lname_field.setToolTipText("Last Name Should be less than 20 Character");
			lname_field.setDocument(new JTextFieldLimit(20));
			lname_field.setPreferredSize(new Dimension(7, 10));
			lname_field.setSize(new Dimension(0, 20));
			lname_field.setBorder(new LineBorder(new Color(192, 192, 192)));
			GridBagConstraints gbc_lname_field = new GridBagConstraints();
			gbc_lname_field.insets = new Insets(0, 0, 5, 5);
			gbc_lname_field.fill = GridBagConstraints.BOTH;
			gbc_lname_field.gridx = 3;
			gbc_lname_field.gridy = 1;
			centerPanel.add(lname_field, gbc_lname_field);
			lname_field.setColumns(10);
			
			JLabel course_label = new JLabel("Select Course :");
			course_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_course_label = new GridBagConstraints();
			gbc_course_label.anchor = GridBagConstraints.WEST;
			gbc_course_label.insets = new Insets(0, 0, 5, 5);
			gbc_course_label.gridx = 1;
			gbc_course_label.gridy = 2;
			centerPanel.add(course_label, gbc_course_label);
			
			JLabel email_label = new JLabel("Email : ");
			GridBagConstraints gbc_email_label = new GridBagConstraints();
			gbc_email_label.anchor = GridBagConstraints.WEST;
			gbc_email_label.insets = new Insets(0, 0, 5, 5);
			gbc_email_label.gridx = 3;
			gbc_email_label.gridy = 2;
			centerPanel.add(email_label, gbc_email_label);
			
			JLabel course_icon = new JLabel(new LoadImage("/resource/course-icon.png"));
			GridBagConstraints gbc_course_icon = new GridBagConstraints();
			gbc_course_icon.anchor = GridBagConstraints.NORTHEAST;
			gbc_course_icon.insets = new Insets(0, 0, 5, 5);
			gbc_course_icon.gridx = 0;
			gbc_course_icon.gridy = 3;
			centerPanel.add(course_icon, gbc_course_icon);
			
			JComboBox<Object> course_combo = new JComboBox<Object>();
			course_combo.setModel(new DefaultComboBoxModel<>(new Courses().availableCourses().keySet().toArray()));
			GridBagConstraints gbc_course_combo = new GridBagConstraints();
			gbc_course_combo.insets = new Insets(0, 0, 5, 5);
			gbc_course_combo.fill = GridBagConstraints.BOTH;
			gbc_course_combo.gridx = 1;
			gbc_course_combo.gridy = 3;
			centerPanel.add(course_combo, gbc_course_combo);
			
			email_field = new JTextField();
			GridBagConstraints gbc_email_field = new GridBagConstraints();
			gbc_email_field.insets = new Insets(0, 0, 5, 5);
			gbc_email_field.fill = GridBagConstraints.BOTH;
			gbc_email_field.gridx = 3;
			gbc_email_field.gridy = 3;
			centerPanel.add(email_field, gbc_email_field);
			email_field.setColumns(10);
			
			JLabel message = new JLabel("");
			GridBagConstraints gbc_message = new GridBagConstraints();
			gbc_message.gridwidth = 5;
			gbc_message.gridx = 0;
			gbc_message.gridy = 5;
			centerPanel.add(message, gbc_message);
			
			JButton register_button = new JButton("Update Student");
			register_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						studentDetails.setFirstName(fname_field.getText());
						studentDetails.setLastName(lname_field.getText());
						studentDetails.setCourseEnrolled((String) course_combo.getSelectedItem());
						studentDetails.setEmail(email_field.getText());
						
						boolean execute = updateStudent(studentDetails);
						
						if (execute) {
							new PopUpMessage("Updated Student Details...");
							dispose();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
						new ErrorGUI("All Fields are Mandatory.");
					}
				}
			});
			GridBagConstraints gbc_register_button = new GridBagConstraints();
			gbc_register_button.gridx = 0;
			gbc_register_button.gridy = 0;
			bottomPanel.add(register_button, gbc_register_button);
			
			studentDetails = getStudentDetails(id);
			
			register_button.setText("Update");
			
			fname_field.setText(studentDetails.getFirstName());
			lname_field.setText(studentDetails.getLastName());
			course_combo.setSelectedItem(studentDetails.getCourseEnrolled());
			email_field.setText(studentDetails.getEmail());
			
			setVisible(true);
		}
			
	}

}
