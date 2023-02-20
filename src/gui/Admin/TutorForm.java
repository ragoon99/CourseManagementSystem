package gui.Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import backend.Modules;
import backend.Tutors;
import backend.Validation;
import backend.Details.TutorDetails;
import exception.ErrorGUI;
import gui.PopUpMessage;
import utils.JTextFieldLimit;
import utils.LoadImage;

public class TutorForm extends Tutors {
	private JTextField fname_field;
	private JTextField lname_field;
	private JPanel contentPane;
	
	private Validation validator = new Validation();
	
	private int id;
	private boolean update;
	
	public TutorForm() {
		this(0, false);
	}
	
	public TutorForm(int id, boolean update) {
		this.id = id;
		this.update = update;
		
		new TutorFormGUI();
	}
	
	private class TutorFormGUI extends JDialog {
		private TutorDetails tutorDetails = new TutorDetails();
		private HashMap<String, String> moduleList = new Modules().moduleList();
		
		public TutorFormGUI() {
			if (update) {
				updateTutorForm();
			} else {
				addTutorForm();
			}	
		}
		
		private void addTutorForm() {
			setMaximumSize(new Dimension(690, 640));
			setTitle("Tutor Form");
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 550, 443);
			setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			
			JPanel topPanel = new JPanel();
			topPanel.setBackground(new Color(255, 255, 255));
			topPanel.setPreferredSize(new Dimension(10, 100));
			topPanel.setSize(new Dimension(0, 100));
			contentPane.add(topPanel, BorderLayout.NORTH);
			topPanel.setLayout(new BorderLayout(0, 0));
			
			JLabel title_label = new JLabel("Tutor Form");
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
			gbl_bottomPanel.columnWidths = new int[]{225, 0};
			gbl_bottomPanel.rowHeights = new int[]{36, 0};
			gbl_bottomPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_bottomPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			bottomPanel.setLayout(gbl_bottomPanel);
			
			JPanel centerPanel = new JPanel();
			centerPanel.setBackground(new Color(255, 255, 255));
			contentPane.add(centerPanel, BorderLayout.CENTER);
			GridBagLayout gbl_centerPanel = new GridBagLayout();
			gbl_centerPanel.columnWidths = new int[]{29, 67, 50, 33, 50, 30, 0};
			gbl_centerPanel.rowHeights = new int[]{35, 30, 31, 30, 27, 30, 0};
			gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			centerPanel.setLayout(gbl_centerPanel);
			
			JLabel fname_label = new JLabel("First Name :");
			fname_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_fname_label = new GridBagConstraints();
			gbc_fname_label.fill = GridBagConstraints.HORIZONTAL;
			gbc_fname_label.insets = new Insets(0, 0, 5, 5);
			gbc_fname_label.gridx = 2;
			gbc_fname_label.gridy = 0;
			centerPanel.add(fname_label, gbc_fname_label);
			
			JLabel lname_label = new JLabel("Last Name :");
			lname_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_lname_label = new GridBagConstraints();
			gbc_lname_label.fill = GridBagConstraints.HORIZONTAL;
			gbc_lname_label.insets = new Insets(0, 0, 5, 5);
			gbc_lname_label.gridx = 4;
			gbc_lname_label.gridy = 0;
			centerPanel.add(lname_label, gbc_lname_label);
			
			JLabel fname_icon = new JLabel(new LoadImage("/resource/name-icon.png", 26, 26));
			GridBagConstraints gbc_fname_icon = new GridBagConstraints();
			gbc_fname_icon.anchor = GridBagConstraints.NORTH;
			gbc_fname_icon.insets = new Insets(0, 0, 5, 5);
			gbc_fname_icon.gridx = 1;
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
			gbc_fname_field.gridx = 2;
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
			gbc_pass_label.fill = GridBagConstraints.HORIZONTAL;
			gbc_pass_label.insets = new Insets(0, 0, 5, 5);
			gbc_pass_label.gridx = 2;
			gbc_pass_label.gridy = 2;
			centerPanel.add(pass_label, gbc_pass_label);
			
			JLabel cPass_label = new JLabel("Confirm Password :");
			cPass_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_cPass_label = new GridBagConstraints();
			gbc_cPass_label.fill = GridBagConstraints.HORIZONTAL;
			gbc_cPass_label.insets = new Insets(0, 0, 5, 5);
			gbc_cPass_label.gridx = 4;
			gbc_cPass_label.gridy = 2;
			centerPanel.add(cPass_label, gbc_cPass_label);
			
			JTextField pass_field = new JTextField();
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
			JLabel pass_icon = new JLabel(new LoadImage("/resource/password-icon.png", 26, 26));
			GridBagConstraints gbc_pass_icon = new GridBagConstraints();
			gbc_pass_icon.anchor = GridBagConstraints.NORTH;
			gbc_pass_icon.insets = new Insets(0, 0, 5, 5);
			gbc_pass_icon.gridx = 1;
			gbc_pass_icon.gridy = 3;
			centerPanel.add(pass_icon, gbc_pass_icon);
			pass_field.setPreferredSize(new Dimension(7, 10));
			pass_field.setSize(new Dimension(0, 30));
			pass_field.setBorder(new LineBorder(new Color(192, 192, 192)));
			GridBagConstraints gbc_pass_field = new GridBagConstraints();
			gbc_pass_field.fill = GridBagConstraints.BOTH;
			gbc_pass_field.insets = new Insets(0, 0, 5, 5);
			gbc_pass_field.gridx = 2;
			gbc_pass_field.gridy = 3;
			centerPanel.add(pass_field, gbc_pass_field);
			pass_field.setColumns(10);
			
			JTextField cPass_field = new JTextField();
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
			
			JLabel email_label = new JLabel("Email :");
			email_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GridBagConstraints gbc_email_label = new GridBagConstraints();
			gbc_email_label.fill = GridBagConstraints.HORIZONTAL;
			gbc_email_label.insets = new Insets(0, 0, 5, 5);
			gbc_email_label.gridx = 2;
			gbc_email_label.gridy = 4;
			centerPanel.add(email_label, gbc_email_label);
			
			JLabel email_icon = new JLabel(new LoadImage("/resource/email-icon.png", 26, 26));
			GridBagConstraints gbc_email_icon = new GridBagConstraints();
			gbc_email_icon.insets = new Insets(0, 0, 0, 5);
			gbc_email_icon.anchor = GridBagConstraints.NORTH;
			gbc_email_icon.gridx = 1;
			gbc_email_icon.gridy = 5;
			centerPanel.add(email_icon, gbc_email_icon);
			
			JTextField email_field = new JTextField();
			email_field.setBorder(new LineBorder(new Color(192, 192, 192)));
			GridBagConstraints gbc_email_field = new GridBagConstraints();
			gbc_email_field.insets = new Insets(0, 0, 0, 5);
			gbc_email_field.fill = GridBagConstraints.BOTH;
			gbc_email_field.gridx = 2;
			gbc_email_field.gridy = 5;
			centerPanel.add(email_field, gbc_email_field);
			email_field.setColumns(10);
			
			JButton register_button = new JButton("Add Tutor");
			register_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String password = pass_field.getText();
						String cPassword = cPass_field.getText();
						
						tutorDetails.setFirstName(fname_field.getText());
						tutorDetails.setLastName(lname_field.getText());
						tutorDetails.setPassword(pass_field.getText());
						tutorDetails.setEmail(email_field.getText());
						
						if(!validator.checkPassword(password, cPassword)) {
							new ErrorGUI("Password Did Not Matched...");							
						}
						
						int tid = generateTutorID();
						tutorDetails.setTutId(tid);
						
						boolean execute = createTutor(tutorDetails);
						
						if (execute) {
							new PopUpMessage("Successfully Added Tutor", "Tutor ID is: " + tutorDetails.getTutId(), "Tutor Password is: " + tutorDetails.getPassword());
							dispose();
						}
					} catch (Exception e2) {
						new ErrorGUI("All Fields are Mandatory.");
						e2.printStackTrace();
					}
				}
			});
			GridBagConstraints gbc_register_button = new GridBagConstraints();
			gbc_register_button.gridx = 0;
			gbc_register_button.gridy = 0;
			bottomPanel.add(register_button, gbc_register_button);
			
			setVisible(true);
		}
		
		private void updateTutorForm()  {
			setTitle("Tutor Form");
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 579, 420);
			setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			
			JPanel topPanel = new JPanel();
			topPanel.setBackground(new Color(255, 255, 255));
			topPanel.setPreferredSize(new Dimension(10, 100));
			topPanel.setSize(new Dimension(0, 100));
			contentPane.add(topPanel, BorderLayout.NORTH);
			topPanel.setLayout(new BorderLayout(0, 0));
			
			JLabel title_label = new JLabel("Update Tutor Details");
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
			gbl_bottomPanel.columnWidths = new int[]{225, 0};
			gbl_bottomPanel.rowHeights = new int[]{36, 0};
			gbl_bottomPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_bottomPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			bottomPanel.setLayout(gbl_bottomPanel);
			
			JPanel centerPanel = new JPanel();
			centerPanel.setBackground(new Color(255, 255, 255));
			contentPane.add(centerPanel, BorderLayout.CENTER);
			GridBagLayout gbl_centerPanel = new GridBagLayout();
			gbl_centerPanel.columnWidths = new int[]{60, 165, 26, 176, 50, 0};
			gbl_centerPanel.rowHeights = new int[]{30, 0, 30, 30, 0};
			gbl_centerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			centerPanel.setLayout(gbl_centerPanel);
			
			JLabel fname_label = new JLabel("First Name :");
			fname_label.setFont(new Font("Tahoma", Font.PLAIN, 10));
			GridBagConstraints gbc_fname_label = new GridBagConstraints();
			gbc_fname_label.anchor = GridBagConstraints.WEST;
			gbc_fname_label.insets = new Insets(0, 0, 5, 5);
			gbc_fname_label.gridx = 1;
			gbc_fname_label.gridy = 0;
			centerPanel.add(fname_label, gbc_fname_label);
			
			JLabel lname_label = new JLabel("Last Name :");
			lname_label.setFont(new Font("Tahoma", Font.PLAIN, 10));
			GridBagConstraints gbc_lname_label = new GridBagConstraints();
			gbc_lname_label.anchor = GridBagConstraints.WEST;
			gbc_lname_label.insets = new Insets(0, 0, 5, 5);
			gbc_lname_label.gridx = 3;
			gbc_lname_label.gridy = 0;
			centerPanel.add(lname_label, gbc_lname_label);
			
			JLabel fname_icon = new JLabel(new LoadImage("/resource/name-icon.png", 26, 26));
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
			gbc_lname_field.fill = GridBagConstraints.BOTH;
			gbc_lname_field.insets = new Insets(0, 0, 5, 5);
			gbc_lname_field.gridx = 3;
			gbc_lname_field.gridy = 1;
			centerPanel.add(lname_field, gbc_lname_field);
			lname_field.setColumns(10);
			
			JLabel email_label = new JLabel("Email : ");
			GridBagConstraints gbc_email_label = new GridBagConstraints();
			gbc_email_label.anchor = GridBagConstraints.WEST;
			gbc_email_label.insets = new Insets(0, 0, 5, 5);
			gbc_email_label.gridx = 1;
			gbc_email_label.gridy = 2;
			centerPanel.add(email_label, gbc_email_label);
			
			JTextField email_field = new JTextField();
			GridBagConstraints gbc_email_field = new GridBagConstraints();
			gbc_email_field.insets = new Insets(0, 0, 0, 5);
			gbc_email_field.fill = GridBagConstraints.BOTH;
			gbc_email_field.gridx = 1;
			gbc_email_field.gridy = 3;
			centerPanel.add(email_field, gbc_email_field);
			email_field.setColumns(10);
			
			JButton register_button = new JButton("Update Tutor");
			register_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						tutorDetails.setFirstName(fname_field.getText());
						tutorDetails.setLastName(lname_field.getText());
						
						boolean execute = updateTutor(tutorDetails);
						
						if (execute) {
							new PopUpMessage("Tutor Details Updated Successfully");
							dispose();
						}
					} catch (Exception e2) {
						new ErrorGUI("All Fields are Mandatory.");
						e2.printStackTrace();
					}
				}
			});
			GridBagConstraints gbc_register_button = new GridBagConstraints();
			gbc_register_button.gridx = 0;
			gbc_register_button.gridy = 0;
			bottomPanel.add(register_button, gbc_register_button);
			
			tutorDetails = getTutorDetails(id);
			
			fname_field.setText(tutorDetails.getFirstName());
			lname_field.setText(tutorDetails.getLastName());
			email_field.setText(tutorDetails.getEmail());

			setVisible(true);
		}

	}

	
}
