package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import backend.Courses;
import backend.Students;
import backend.UserLogin;
import backend.Validation;
import backend.Details.LoginDetails;
import backend.Details.StudentDetails;
import exception.ErrorGUI;
import gui.Admin.AdminHomepage;
import gui.Student.StudentHomepage;
import gui.Tutor.TutorHomepage;
import utils.CustomFrame;
import utils.JTextFieldLimit;
import utils.LoadImage;

public class LoginSignup extends CustomFrame {

	private JPanel contentPane;
	private JTextField email_field;
	private JPasswordField password_field;
	private JTextField fname_field;
	private JTextField address_field;
	private JTextField pass_field;
	private JTextField lname_field;
	private JTextField cPass_field;
	private JTextField city_field;
	private JTextField usrId_field;
	
	private Validation validator = new Validation();
	private LoginDetails details = new LoginDetails();
	
	/**
	 * Create the frame.
	 */
	public LoginSignup() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setSize(new Dimension(500, 600));
		setLocationRelativeTo(null);
		setPreferredSize(new Dimension(500, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		contentPane.setPreferredSize(new Dimension(500, 600));
		contentPane.setSize(new Dimension(500, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		loginComponent();
		
		setVisible(true);
	}
	
	public void loginComponent() {
		setTitle("Login - Course Management System");
		
		JPanel top_panel = new JPanel();
		top_panel.setBackground(new Color(255, 255, 255));
		contentPane.add(top_panel, BorderLayout.NORTH);
		top_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel title_label = new JLabel("LOGIN");
		title_label.setPreferredSize(new Dimension(43, 100));
		title_label.setFont(new Font("Poppins SemiBold", Font.PLAIN, 26));
		title_label.setHorizontalTextPosition(SwingConstants.CENTER);
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		top_panel.add(title_label);
		
		JPanel center_panel = new JPanel();
		center_panel.setBackground(new Color(255, 255, 255));
		contentPane.add(center_panel, BorderLayout.CENTER);
		GridBagLayout gbl_center_panel = new GridBagLayout();
		gbl_center_panel.columnWidths = new int[]{135, 43, 145, 36, 109, 0, 0};
		gbl_center_panel.rowHeights = new int[]{62, 19, 30, 42, 19, 30, 41, 21, 30, 0};
		gbl_center_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_center_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		center_panel.setLayout(gbl_center_panel);
		
		JLabel usrId_label = new JLabel("User ID or Email :");
		GridBagConstraints gbc_usrId_label = new GridBagConstraints();
		gbc_usrId_label.anchor = GridBagConstraints.WEST;
		gbc_usrId_label.insets = new Insets(0, 0, 5, 5);
		gbc_usrId_label.gridx = 2;
		gbc_usrId_label.gridy = 1;
		center_panel.add(usrId_label, gbc_usrId_label);
		
		JLabel userIcon = new JLabel(new LoadImage("/resource/user-icon.png"));
		GridBagConstraints gbc_userIcon = new GridBagConstraints();
		gbc_userIcon.insets = new Insets(0, 0, 5, 5);
		gbc_userIcon.gridx = 1;
		gbc_userIcon.gridy = 2;
		center_panel.add(userIcon, gbc_userIcon);
		
		usrId_field = new JTextField();
		GridBagConstraints gbc_usrId_field = new GridBagConstraints();
		gbc_usrId_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_usrId_field.insets = new Insets(0, 0, 5, 5);
		gbc_usrId_field.gridx = 2;
		gbc_usrId_field.gridy = 2;
		center_panel.add(usrId_field, gbc_usrId_field);
		usrId_field.setColumns(10);
		
		JLabel pass_label = new JLabel("Password :");
		GridBagConstraints gbc_pass_label = new GridBagConstraints();
		gbc_pass_label.anchor = GridBagConstraints.WEST;
		gbc_pass_label.insets = new Insets(0, 0, 5, 5);
		gbc_pass_label.gridx = 2;
		gbc_pass_label.gridy = 4;
		center_panel.add(pass_label, gbc_pass_label);
		
		JLabel password_icon = new JLabel(new LoadImage("/resource/password-icon.png"));
		GridBagConstraints gbc_password_icon = new GridBagConstraints();
		gbc_password_icon.insets = new Insets(0, 0, 5, 5);
		gbc_password_icon.gridx = 1;
		gbc_password_icon.gridy = 5;
		center_panel.add(password_icon, gbc_password_icon);
		
		password_field = new JPasswordField();
		password_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (password_field.getPassword().length < 4) {
					password_field.setBorder(new LineBorder(Color.red));
				} else {
					password_field.setBorder(new LineBorder(Color.gray, 1));
				}
			} 
		});
		password_field.setEchoChar('*');
		GridBagConstraints gbc_pass_field = new GridBagConstraints();
		gbc_pass_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_pass_field.insets = new Insets(0, 0, 5, 5);
		gbc_pass_field.gridx = 2;
		gbc_pass_field.gridy = 5;
		center_panel.add(password_field, gbc_pass_field);
		
		JCheckBox showPass = new JCheckBox(new LoadImage("/resource/hidePass-icon.png"));
		showPass.setToolTipText("Show Password");
		showPass.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					showPass.setIcon(new LoadImage("/resource/showPass-icon.png"));
					password_field.setEchoChar((char) 0);
				} else {
					showPass.setIcon(new LoadImage("/resource/hidePass-icon.png"));
					password_field.setEchoChar('*');
				}
			}
		});
		showPass.setBackground(null);
		GridBagConstraints gbc_showPass = new GridBagConstraints();
		gbc_showPass.insets = new Insets(0, 0, 5, 5);
		gbc_showPass.gridx = 3;
		gbc_showPass.gridy = 5;
		center_panel.add(showPass, gbc_showPass);
		
		JLabel usrSelect_label = new JLabel("Select User :");
		GridBagConstraints gbc_usrSelect_label = new GridBagConstraints();
		gbc_usrSelect_label.anchor = GridBagConstraints.WEST;
		gbc_usrSelect_label.insets = new Insets(0, 0, 5, 5);
		gbc_usrSelect_label.gridx = 2;
		gbc_usrSelect_label.gridy = 7;
		center_panel.add(usrSelect_label, gbc_usrSelect_label);
		
		JLabel userType_icon = new JLabel(new LoadImage("/resource/userSelect-icon.png"));
		GridBagConstraints gbc_userType_icon = new GridBagConstraints();
		gbc_userType_icon.insets = new Insets(0, 0, 0, 5);
		gbc_userType_icon.anchor = GridBagConstraints.NORTH;
		gbc_userType_icon.gridx = 1;
		gbc_userType_icon.gridy = 8;
		center_panel.add(userType_icon, gbc_userType_icon);
		
		JComboBox<String> usrSelect_combo = new JComboBox<String>();
		usrSelect_combo.setToolTipText("Select Type of the User");
		usrSelect_combo.setModel(new DefaultComboBoxModel<String>(new String[] {"Student", "Tutor", "Admin"}));
		GridBagConstraints gbc_usrSelect_combo = new GridBagConstraints();
		gbc_usrSelect_combo.fill = GridBagConstraints.BOTH;
		gbc_usrSelect_combo.insets = new Insets(0, 0, 0, 5);
		gbc_usrSelect_combo.gridx = 2;
		gbc_usrSelect_combo.gridy = 8;
		center_panel.add(usrSelect_combo, gbc_usrSelect_combo);
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setPreferredSize(new Dimension(10, 100));
		bottom_panel.setBackground(new Color(255, 255, 255));
		contentPane.add(bottom_panel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottom_panel = new GridBagLayout();
		gbl_bottom_panel.columnWidths = new int[]{266, 0};
		gbl_bottom_panel.rowHeights = new int[]{21, 30, 0};
		gbl_bottom_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bottom_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		bottom_panel.setLayout(gbl_bottom_panel);
		
		JButton login_button = new JButton("Login");
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					details.setUid(Integer.parseInt(usrId_field.getText()));
					details.setPassword(String.valueOf(password_field.getPassword()));
					
					if (usrSelect_combo.getSelectedItem() == "Student") {
						details.setTableName(details.getStudentTable());
						boolean state = new UserLogin().userLogin(details);
						
						if (state) {
							new PopUpMessage("Logged In Successfully!");
							new StudentHomepage(details.getUid());
							dispose();
						}
						else new ErrorGUI("ID and Password Does not match...");
					}
					
					if (usrSelect_combo.getSelectedItem() == "Tutor") {
						details.setTableName(details.getTutorTable());
						boolean state = new UserLogin().userLogin(details);
						
						if (state) {
							new PopUpMessage("Logged In Successfully!");
							new TutorHomepage(details.getUid());
							dispose();
						}
						else new ErrorGUI("ID and Password Does not match...");
					}
					
					if (usrSelect_combo.getSelectedItem() == "Admin") {
						details.setTableName(details.getAdminTable());
						boolean state = new UserLogin().userLogin(details);
						
						if (state) {
							new PopUpMessage("Logged In Successfully!");
							new AdminHomepage(details.getUid());
							
							dispose();
						}
						else new ErrorGUI("ID and Password Does not match...");
					}
				} catch (Exception e2) {
					if (e2 instanceof NumberFormatException) {
						new ErrorGUI("ID and Password Does not Match...");
					} else {						
						new ErrorGUI("Username and Password Field Cannot be Empty.");
					}
				}
			}
		});
		GridBagConstraints gbc_login_button = new GridBagConstraints();
		gbc_login_button.anchor = GridBagConstraints.NORTH;
		gbc_login_button.insets = new Insets(0, 0, 5, 0);
		gbc_login_button.gridx = 0;
		gbc_login_button.gridy = 0;
		bottom_panel.add(login_button, gbc_login_button);
		
		JButton register_button = new JButton("Don't Have An Account? Press Here");
		register_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				signupComponent();
				contentPane.updateUI();
			}
		});
		register_button.setOpaque(false);
		register_button.setBorder(null);
		register_button.setBackground(null);
		Font font = register_button.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		register_button.setFont(font.deriveFont(attributes));
		
		usrSelect_combo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (usrSelect_combo.getSelectedItem().equals("Student")) {
					register_button.setVisible(true);
				} else {
					register_button.setVisible(false);
				}
			}
		});
		
		GridBagConstraints gbc_register_button = new GridBagConstraints();
		gbc_register_button.anchor = GridBagConstraints.NORTH;
		gbc_register_button.gridx = 0;
		gbc_register_button.gridy = 1;
		bottom_panel.add(register_button, gbc_register_button);
	}
	
	public void signupComponent() {
		setTitle("Signup - Course Management System");
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 255, 255));
		topPanel.setPreferredSize(new Dimension(10, 100));
		topPanel.setSize(new Dimension(0, 100));
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel title_label = new JLabel("Register");
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
		gbl_bottomPanel.rowHeights = new int[]{36, 0, 0};
		gbl_bottomPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bottomPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		bottomPanel.setLayout(gbl_bottomPanel);
		
		JButton login_button = new JButton("Already Have An Account? Press Here");
		Font font = login_button.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		login_button.setFont(font.deriveFont(attributes));
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				loginComponent();
				contentPane.updateUI();
			}
		});
		
		JButton register_button = new JButton("Register");
		GridBagConstraints gbc_register_button = new GridBagConstraints();
		gbc_register_button.anchor = GridBagConstraints.NORTH;
		gbc_register_button.insets = new Insets(0, 0, 5, 0);
		gbc_register_button.gridx = 0;
		gbc_register_button.gridy = 0;
		bottomPanel.add(register_button, gbc_register_button);
		
		register_button.setEnabled(false);
		
		
		login_button.setBackground(null);
		login_button.setBorderPainted(false);
		GridBagConstraints gbc_login_button = new GridBagConstraints();
		gbc_login_button.anchor = GridBagConstraints.NORTH;
		gbc_login_button.gridx = 0;
		gbc_login_button.gridy = 1;
		bottomPanel.add(login_button, gbc_login_button);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{30, 30, 120, 33, 34, 120, 30, 0};
		gbl_centerPanel.rowHeights = new int[]{35, 25, 31, 25, 27, 25, 28, 0, 24, 0, 20, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JLabel fname_label = new JLabel("First Name :");
		fname_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_fname_label = new GridBagConstraints();
		gbc_fname_label.anchor = GridBagConstraints.WEST;
		gbc_fname_label.insets = new Insets(0, 0, 5, 5);
		gbc_fname_label.gridx = 2;
		gbc_fname_label.gridy = 0;
		centerPanel.add(fname_label, gbc_fname_label);
		
		JLabel lname_label = new JLabel("Last Name :");
		lname_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_lname_label = new GridBagConstraints();
		gbc_lname_label.anchor = GridBagConstraints.WEST;
		gbc_lname_label.insets = new Insets(0, 0, 5, 5);
		gbc_lname_label.gridx = 5;
		gbc_lname_label.gridy = 0;
		centerPanel.add(lname_label, gbc_lname_label);
		
		JLabel fname_icon = new JLabel(new LoadImage("/resource/name-icon.png") );
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
		gbc_lname_field.gridx = 5;
		gbc_lname_field.gridy = 1;
		centerPanel.add(lname_field, gbc_lname_field);
		lname_field.setColumns(10);
		
		JLabel address_label = new JLabel("Street Address :");
		address_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_address_label = new GridBagConstraints();
		gbc_address_label.anchor = GridBagConstraints.WEST;
		gbc_address_label.insets = new Insets(0, 0, 5, 5);
		gbc_address_label.gridx = 2;
		gbc_address_label.gridy = 2;
		centerPanel.add(address_label, gbc_address_label);
		
		JLabel city_label = new JLabel("City :");
		city_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_city_label = new GridBagConstraints();
		gbc_city_label.anchor = GridBagConstraints.WEST;
		gbc_city_label.insets = new Insets(0, 0, 5, 5);
		gbc_city_label.gridx = 5;
		gbc_city_label.gridy = 2;
		centerPanel.add(city_label, gbc_city_label);
		
		JLabel address_icon = new JLabel(new LoadImage("/resource/address-icon.png"));
		GridBagConstraints gbc_address_icon = new GridBagConstraints();
		gbc_address_icon.anchor = GridBagConstraints.NORTH;
		gbc_address_icon.insets = new Insets(0, 0, 5, 5);
		gbc_address_icon.gridx = 1;
		gbc_address_icon.gridy = 3;
		centerPanel.add(address_icon, gbc_address_icon);
		
		address_field = new JTextField();
		address_field.setDocument(new JTextFieldLimit(20));
		address_field.setPreferredSize(new Dimension(7, 10));
		address_field.setSize(new Dimension(0, 20));
		address_field.setBorder(new LineBorder(new Color(192, 192, 192)));
		GridBagConstraints gbc_address_field = new GridBagConstraints();
		gbc_address_field.fill = GridBagConstraints.BOTH;
		gbc_address_field.insets = new Insets(0, 0, 5, 5);
		gbc_address_field.gridx = 2;
		gbc_address_field.gridy = 3;
		centerPanel.add(address_field, gbc_address_field);
		address_field.setColumns(10);
		
		city_field = new JTextField();
		city_field.setDocument(new JTextFieldLimit(30));
		city_field.setPreferredSize(new Dimension(7, 10));
		city_field.setSize(new Dimension(0, 20));
		city_field.setBorder(new LineBorder(new Color(192, 192, 192)));
		GridBagConstraints gbc_city_field = new GridBagConstraints();
		gbc_city_field.insets = new Insets(0, 0, 5, 5);
		gbc_city_field.fill = GridBagConstraints.BOTH;
		gbc_city_field.gridx = 5;
		gbc_city_field.gridy = 3;
		centerPanel.add(city_field, gbc_city_field);
		city_field.setColumns(10);
		
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
		
		JLabel pass_label = new JLabel("Password :");
		pass_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_pass_label = new GridBagConstraints();
		gbc_pass_label.anchor = GridBagConstraints.WEST;
		gbc_pass_label.insets = new Insets(0, 0, 5, 5);
		gbc_pass_label.gridx = 2;
		gbc_pass_label.gridy = 4;
		centerPanel.add(pass_label, gbc_pass_label);
		
		JLabel cPass_label = new JLabel("Confirm Password :");
		cPass_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_cPass_label = new GridBagConstraints();
		gbc_cPass_label.anchor = GridBagConstraints.WEST;
		gbc_cPass_label.insets = new Insets(0, 0, 5, 5);
		gbc_cPass_label.gridx = 5;
		gbc_cPass_label.gridy = 4;
		centerPanel.add(cPass_label, gbc_cPass_label);
		
		JLabel pass_icon = new JLabel(new LoadImage("/resource/password-icon.png"));
		GridBagConstraints gbc_pass_icon = new GridBagConstraints();
		gbc_pass_icon.anchor = GridBagConstraints.NORTH;
		gbc_pass_icon.insets = new Insets(0, 0, 5, 5);
		gbc_pass_icon.gridx = 1;
		gbc_pass_icon.gridy = 5;
		centerPanel.add(pass_icon, gbc_pass_icon);
		pass_field.setPreferredSize(new Dimension(7, 10));
		pass_field.setSize(new Dimension(0, 30));
		pass_field.setBorder(new LineBorder(new Color(192, 192, 192)));
		GridBagConstraints gbc_pass_field = new GridBagConstraints();
		gbc_pass_field.fill = GridBagConstraints.BOTH;
		gbc_pass_field.insets = new Insets(0, 0, 5, 5);
		gbc_pass_field.gridx = 2;
		gbc_pass_field.gridy = 5;
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
		gbc_cPass_field.gridx = 5;
		gbc_cPass_field.gridy = 5;
		centerPanel.add(cPass_field, gbc_cPass_field);
		cPass_field.setColumns(10);
		
		JLabel dob_label = new JLabel("DOB :");
		dob_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_dob_label = new GridBagConstraints();
		gbc_dob_label.anchor = GridBagConstraints.WEST;
		gbc_dob_label.insets = new Insets(0, 0, 5, 5);
		gbc_dob_label.gridx = 2;
		gbc_dob_label.gridy = 6;
		centerPanel.add(dob_label, gbc_dob_label);
		
		JLabel gender_label = new JLabel("Gender :");
		gender_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_gender_label = new GridBagConstraints();
		gbc_gender_label.anchor = GridBagConstraints.WEST;
		gbc_gender_label.insets = new Insets(0, 0, 5, 5);
		gbc_gender_label.gridx = 5;
		gbc_gender_label.gridy = 6;
		centerPanel.add(gender_label, gbc_gender_label);
		
		JLabel dob_icon = new JLabel(new LoadImage("/resource/dob-icon.png") );
		GridBagConstraints gbc_dob_icon = new GridBagConstraints();
		gbc_dob_icon.anchor = GridBagConstraints.NORTH;
		gbc_dob_icon.insets = new Insets(0, 0, 5, 5);
		gbc_dob_icon.gridx = 1;
		gbc_dob_icon.gridy = 7;
		centerPanel.add(dob_icon, gbc_dob_icon);
		
		JDateChooser dob_field = new JDateChooser();
		dob_field.setDateFormatString("yyyy-MM-dd");
		GridBagConstraints gbc_dob_field = new GridBagConstraints();
		gbc_dob_field.insets = new Insets(0, 0, 5, 5);
		gbc_dob_field.fill = GridBagConstraints.BOTH;
		gbc_dob_field.gridx = 2;
		gbc_dob_field.gridy = 7;
		centerPanel.add(dob_field, gbc_dob_field);
		
		ImageIcon maleImage = new LoadImage("/resource/male-icon.png");
		ImageIcon femaleImage = new LoadImage("/resource/female-icon.png");
		ImageIcon otherImage = new LoadImage("/resource/thirdGender-icon.png");

		JLabel gender_icon = new JLabel();
		GridBagConstraints gbc_gender_icon = new GridBagConstraints();
		gbc_gender_icon.anchor = GridBagConstraints.NORTH;
		gbc_gender_icon.insets = new Insets(0, 0, 5, 5);
		gbc_gender_icon.gridx = 4;
		gbc_gender_icon.gridy = 7;
		centerPanel.add(gender_icon, gbc_gender_icon);
		
		
		JComboBox<String> gender_combo = new JComboBox<String>();
		gender_combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(gender_combo.getSelectedItem() == "Male") {
					gender_icon.setIcon(maleImage);
				}
				if(gender_combo.getSelectedItem() == "Female") {
					gender_icon.setIcon(femaleImage);
				}
				if(gender_combo.getSelectedItem() == "Other") {
					gender_icon.setIcon(otherImage);
				}
			}
		});
		
		gender_combo.setModel(new DefaultComboBoxModel<String>(new String[] {"Male", "Female", "Other"}));
		GridBagConstraints gbc_gender_combo = new GridBagConstraints();
		gbc_gender_combo.insets = new Insets(0, 0, 5, 5);
		gbc_gender_combo.fill = GridBagConstraints.BOTH;
		gbc_gender_combo.gridx = 5;
		gbc_gender_combo.gridy = 7;
		centerPanel.add(gender_combo, gbc_gender_combo);
		
		JLabel course_label = new JLabel("Select Course :");
		course_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_course_label = new GridBagConstraints();
		gbc_course_label.anchor = GridBagConstraints.WEST;
		gbc_course_label.insets = new Insets(0, 0, 5, 5);
		gbc_course_label.gridx = 2;
		gbc_course_label.gridy = 8;
		centerPanel.add(course_label, gbc_course_label);
		
		JLabel email_label = new JLabel("Email :");
		email_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_email_label = new GridBagConstraints();
		gbc_email_label.anchor = GridBagConstraints.WEST;
		gbc_email_label.insets = new Insets(0, 0, 5, 5);
		gbc_email_label.gridx = 5;
		gbc_email_label.gridy = 8;
		centerPanel.add(email_label, gbc_email_label);
		
		JLabel course_icon = new JLabel(new LoadImage("/resource/course-icon.png"));
		GridBagConstraints gbc_course_icon = new GridBagConstraints();
		gbc_course_icon.anchor = GridBagConstraints.NORTH;
		gbc_course_icon.insets = new Insets(0, 0, 5, 5);
		gbc_course_icon.gridx = 1;
		gbc_course_icon.gridy = 9;
		centerPanel.add(course_icon, gbc_course_icon);
		
		Object courses[] = new Courses().availableCourses().keySet().toArray();
		JComboBox<Object> course_combo = new JComboBox<Object>();
		course_combo.setModel(new DefaultComboBoxModel<Object>(courses));
		GridBagConstraints gbc_course_combo = new GridBagConstraints();
		gbc_course_combo.insets = new Insets(0, 0, 5, 5);
		gbc_course_combo.fill = GridBagConstraints.BOTH;
		gbc_course_combo.gridx = 2;
		gbc_course_combo.gridy = 9;
		centerPanel.add(course_combo, gbc_course_combo);
		
		JLabel email_icon = new JLabel(new LoadImage("/resource/email-icon.png"));
		GridBagConstraints gbc_email_icon = new GridBagConstraints();
		gbc_email_icon.insets = new Insets(0, 0, 5, 5);
		gbc_email_icon.anchor = GridBagConstraints.NORTHEAST;
		gbc_email_icon.gridx = 4;
		gbc_email_icon.gridy = 9;
		centerPanel.add(email_icon, gbc_email_icon);
		
		JLabel message = new JLabel("");
		GridBagConstraints gbc_message = new GridBagConstraints();
		gbc_message.gridwidth = 6;
		gbc_message.gridx = 1;
		gbc_message.gridy = 11;
		centerPanel.add(message, gbc_message);
		
		email_field = new JTextField();
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
		GridBagConstraints gbc_email_field = new GridBagConstraints();
		gbc_email_field.insets = new Insets(0, 0, 5, 5);
		gbc_email_field.fill = GridBagConstraints.BOTH;
		gbc_email_field.gridx = 5;
		gbc_email_field.gridy = 9;
		centerPanel.add(email_field, gbc_email_field);
		email_field.setColumns(10);
		
		register_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StudentDetails studentDetails = new StudentDetails();
					Students students = new Students();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String pass = pass_field.getText();
					String cPass = cPass_field.getText();
					
					studentDetails.setStdId(students.generateStudentID());
					studentDetails.setFirstName(fname_field.getText());
					studentDetails.setLastName(lname_field.getText());
					studentDetails.setDob(Date.valueOf(format.format(dob_field.getDate())));
					studentDetails.setAddress(address_field.getText() + ", " + city_field.getText());
					studentDetails.setGender((String) gender_combo.getSelectedItem());
					studentDetails.setCourseEnrolled((String) course_combo.getSelectedItem());
					studentDetails.setEmail(email_field.getText());
					
					if (!validator.checkPassword(pass, cPass)) {
						new ErrorGUI("Password Does Not Match");
						return;
					}
					studentDetails.setPassword(pass);
					
					boolean execute = new Students().createStudent(studentDetails);
					
					if (execute) {
						new PopUpMessage("Successfully Created Account", "Your UserID is: " + studentDetails.getStdId(), "Your Password is: " + studentDetails.getPassword());
						loginComponent();
					} else {
						new ErrorGUI("Error While Creating Account");
					}
					
				} catch (Exception e2) {
					new ErrorGUI("All Fields are Mandatory.");
					e2.printStackTrace();
				}
			}
		});
		
		if (courses.length == 0) {
			register_button.setEnabled(false);
			message.setText("Currently There are No Courses Available");
		}
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
		  .addKeyEventDispatcher((KeyEventDispatcher) new KeyEventDispatcher() {
		      @Override
		      public boolean dispatchKeyEvent(KeyEvent e) {
		    	  if (fname_field.getText().isEmpty() || lname_label.getText().isEmpty() || 
		    			  address_field.getText().isEmpty() || city_field.getText().isEmpty() || 
		    			  pass_field.getText().isEmpty() || cPass_field.getText().isEmpty() || 
		    			  email_field.getText().isEmpty()) {
						register_button.setEnabled(false);
		    	  } else {
						register_button.setEnabled(true);
		    	  }
		    	  
		    	  return false;
		      }
		  });
	}
}
