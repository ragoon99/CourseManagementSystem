package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.sql.Date;
import java.util.Map;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import backend.UserLogin;
import db.Create;
import db.Read;
import exception.ErrorGUI;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;

import utils.*;

public class LoginSignup extends JFrame {

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
	
	private final int admin = 0;
	private final int student = 1;
	private final int tutor = 2;
	public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * Create the frame.
	 */
	public LoginSignup() {
		setVisible(true);
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setSize(new Dimension(500, 600));
		setPreferredSize(new Dimension(500, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(500, 600));
		contentPane.setSize(new Dimension(500, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		loginComponent();
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
		gbl_center_panel.rowHeights = new int[]{62, 19, 36, 42, 19, 36, 41, 21, 0, 0};
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
		
		ImageIcon userImageIcon = new ImageIcon(getClass().getResource("/resource/profile-user.png"));
		Image userImage = userImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING);
		JLabel userIcon = new JLabel(new ImageIcon(userImage));
		GridBagConstraints gbc_userIcon = new GridBagConstraints();
		gbc_userIcon.insets = new Insets(0, 0, 5, 5);
		gbc_userIcon.anchor = GridBagConstraints.NORTH;
		gbc_userIcon.gridx = 1;
		gbc_userIcon.gridy = 2;
		center_panel.add(userIcon, gbc_userIcon);
		
		usrId_field = new JTextField();
		usrId_field.setSize(new Dimension(100, 0));
		usrId_field.setPreferredSize(new Dimension(100, 19));
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
		
		ImageIcon passImageIcon = new ImageIcon(getClass().getResource("/resource/padlock.png"));
		Image passImage = passImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING);
		JLabel password_icon = new JLabel(new ImageIcon(passImage));
		GridBagConstraints gbc_password_icon = new GridBagConstraints();
		gbc_password_icon.insets = new Insets(0, 0, 5, 5);
		gbc_password_icon.anchor = GridBagConstraints.NORTH;
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
		
		ImageIcon showPassImageIcon = new ImageIcon(getClass().getResource("/resource/eye.png"));
		Image showPassImage = showPassImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING);
		ImageIcon hidePassImageIcon = new ImageIcon(getClass().getResource("/resource/hidden.png"));
		Image hidePassImage = hidePassImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING);
		JCheckBox showPass = new JCheckBox(new ImageIcon(hidePassImage));
		showPass.setToolTipText("Show Password");
		showPass.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					showPass.setIcon(new ImageIcon(showPassImage));
					password_field.setEchoChar((char) 0);
				} else {
					showPass.setIcon(new ImageIcon(hidePassImage));
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
		
		ImageIcon userTypeImageIcon = new ImageIcon(getClass().getResource("/resource/customer.png"));
		Image userTypeImage = userTypeImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING);
		JLabel userType_icon = new JLabel(new ImageIcon(userTypeImage));
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
		gbc_usrSelect_combo.fill = GridBagConstraints.HORIZONTAL;
		gbc_usrSelect_combo.insets = new Insets(0, 0, 0, 5);
		gbc_usrSelect_combo.gridx = 2;
		gbc_usrSelect_combo.gridy = 8;
		center_panel.add(usrSelect_combo, gbc_usrSelect_combo);
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setBackground(new Color(255, 255, 255));
		contentPane.add(bottom_panel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottom_panel = new GridBagLayout();
		gbl_bottom_panel.columnWidths = new int[]{145, 266, 139, 0};
		gbl_bottom_panel.rowHeights = new int[]{21, 21, 0, 0, 0};
		gbl_bottom_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bottom_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		bottom_panel.setLayout(gbl_bottom_panel);
		
		JButton login_button = new JButton("Login");
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String uidString = usrId_field.getText();
					String pass = String.valueOf(password_field.getPassword());
					
					if (usrSelect_combo.getSelectedItem() == "Student") {
						boolean state = new UserLogin().studentLogin(uidString, pass);
						
						if (state) {
							new PopUpMessage("Logged In Successfully!");
							new HomePage(student);
							dispose();
						}
						else new ErrorGUI("ID and Password Does not match...");
					}
					
					if (usrSelect_combo.getSelectedItem() == "Tutor") {
						boolean state = new UserLogin().tutorLogin(uidString, pass);
						
						if (state) {
							new PopUpMessage("Logged In Successfully!");
							new HomePage(tutor);
							dispose();
						}
						else new ErrorGUI("ID and Password Does not match...");
					}
					
					if (usrSelect_combo.getSelectedItem() == "Admin") {
						boolean state = new UserLogin().adminLogin(uidString, pass);
						
						if (state) {
							new PopUpMessage("Logged In Successfully!");
							new HomePage(admin);
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
		gbc_login_button.insets = new Insets(0, 0, 5, 5);
		gbc_login_button.gridx = 1;
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
		gbc_register_button.insets = new Insets(0, 0, 5, 5);
		gbc_register_button.anchor = GridBagConstraints.NORTH;
		gbc_register_button.gridx = 1;
		gbc_register_button.gridy = 2;
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
		gbl_bottomPanel.columnWidths = new int[]{146, 85, 85, 0};
		gbl_bottomPanel.rowHeights = new int[]{36, 36, 0, 0};
		gbl_bottomPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bottomPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		login_button.setBackground(null);
		login_button.setBorderPainted(false);
		GridBagConstraints gbc_login_button = new GridBagConstraints();
		gbc_login_button.insets = new Insets(0, 0, 0, 5);
		gbc_login_button.anchor = GridBagConstraints.NORTH;
		gbc_login_button.gridx = 1;
		gbc_login_button.gridy = 2;
		bottomPanel.add(login_button, gbc_login_button);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{45, 67, 21, 60, 33, 34, 0, 66, 37, 0};
		gbl_centerPanel.rowHeights = new int[]{35, 25, 31, 25, 27, 25, 28, 0, 24, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		ImageIcon fnameImageIcon = new ImageIcon(getClass().getResource("/resource/id-card.png"));
		Image fnameImage = fnameImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
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
		gbc_lname_label.gridx = 6;
		gbc_lname_label.gridy = 0;
		centerPanel.add(lname_label, gbc_lname_label);
		JLabel fname_icon = new JLabel(new ImageIcon(fnameImage));
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
		gbc_fname_field.gridwidth = 2;
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
		gbc_lname_field.gridwidth = 2;
		gbc_lname_field.insets = new Insets(0, 0, 5, 5);
		gbc_lname_field.fill = GridBagConstraints.BOTH;
		gbc_lname_field.gridx = 6;
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
		gbc_city_label.gridx = 6;
		gbc_city_label.gridy = 2;
		centerPanel.add(city_label, gbc_city_label);
		
		ImageIcon addressImageIcon = new ImageIcon(getClass().getResource("/resource/city.png"));
		Image addressImage = addressImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		JLabel address_icon = new JLabel(new ImageIcon(addressImage));
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
		gbc_address_field.gridwidth = 2;
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
		gbc_city_field.gridwidth = 2;
		gbc_city_field.insets = new Insets(0, 0, 5, 5);
		gbc_city_field.fill = GridBagConstraints.BOTH;
		gbc_city_field.gridx = 6;
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
		gbc_cPass_label.gridx = 6;
		gbc_cPass_label.gridy = 4;
		centerPanel.add(cPass_label, gbc_cPass_label);
		
		ImageIcon passImageIcon = new ImageIcon(getClass().getResource("/resource/padlock.png"));
		Image passImage = passImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		JLabel pass_icon = new JLabel(new ImageIcon(passImage));
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
		gbc_pass_field.gridwidth = 2;
		gbc_pass_field.fill = GridBagConstraints.BOTH;
		gbc_pass_field.insets = new Insets(0, 0, 5, 5);
		gbc_pass_field.gridx = 2;
		gbc_pass_field.gridy = 5;
		centerPanel.add(pass_field, gbc_pass_field);
		pass_field.setColumns(10);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
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
		gbc_cPass_field.gridwidth = 2;
		gbc_cPass_field.insets = new Insets(0, 0, 5, 5);
		gbc_cPass_field.fill = GridBagConstraints.BOTH;
		gbc_cPass_field.gridx = 6;
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
		gbc_gender_label.gridx = 6;
		gbc_gender_label.gridy = 6;
		centerPanel.add(gender_label, gbc_gender_label);
		
		ImageIcon dobImageIcon = new ImageIcon(getClass().getResource("/resource/birthday-cake.png"));
		Image dobImage = dobImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		JLabel dob_icon = new JLabel(new ImageIcon(dobImage));
		GridBagConstraints gbc_dob_icon = new GridBagConstraints();
		gbc_dob_icon.anchor = GridBagConstraints.NORTH;
		gbc_dob_icon.insets = new Insets(0, 0, 5, 5);
		gbc_dob_icon.gridx = 1;
		gbc_dob_icon.gridy = 7;
		centerPanel.add(dob_icon, gbc_dob_icon);
		JDatePickerImpl dob_field = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		dob_field.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 9));
		GridBagConstraints gbc_dob_field = new GridBagConstraints();
		gbc_dob_field.gridwidth = 2;
		gbc_dob_field.insets = new Insets(0, 0, 5, 5);
		gbc_dob_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_dob_field.gridx = 2;
		gbc_dob_field.gridy = 7;
		centerPanel.add(dob_field, gbc_dob_field);
		
		ImageIcon maleImageIcon = new ImageIcon(getClass().getResource("/resource/mars.png"));
		Image maleImage = maleImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon femaleImageIcon = new ImageIcon(getClass().getResource("/resource/femenine.png"));
		Image femaleImage = femaleImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon otherImageIcon = new ImageIcon(getClass().getResource("/resource/third-gender.png"));
		Image otherImage = otherImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		JLabel gender_icon = new JLabel(new ImageIcon(maleImage));
		GridBagConstraints gbc_gender_icon = new GridBagConstraints();
		gbc_gender_icon.anchor = GridBagConstraints.NORTH;
		gbc_gender_icon.insets = new Insets(0, 0, 5, 5);
		gbc_gender_icon.gridx = 5;
		gbc_gender_icon.gridy = 7;
		centerPanel.add(gender_icon, gbc_gender_icon);
		
		JComboBox<String> gender_combo = new JComboBox<String>();
		gender_combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(gender_combo.getSelectedItem() == "Male") {
					gender_icon.setIcon(new ImageIcon(maleImage));
				}
				if(gender_combo.getSelectedItem() == "Female") {
					gender_icon.setIcon(new ImageIcon(femaleImage));
				}
				if(gender_combo.getSelectedItem() == "Other") {
					gender_icon.setIcon(new ImageIcon(otherImage));
				}
			}
		});
		
		gender_combo.setModel(new DefaultComboBoxModel<String>(new String[] {"Male", "Female", "Other"}));
		GridBagConstraints gbc_gender_combo = new GridBagConstraints();
		gbc_gender_combo.gridwidth = 2;
		gbc_gender_combo.insets = new Insets(0, 0, 5, 5);
		gbc_gender_combo.fill = GridBagConstraints.BOTH;
		gbc_gender_combo.gridx = 6;
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
		gbc_email_label.gridx = 6;
		gbc_email_label.gridy = 8;
		centerPanel.add(email_label, gbc_email_label);
		
		ImageIcon courseImageIcon = new ImageIcon(getClass().getResource("/resource/online-course.png"));
		Image courseImage = courseImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		JLabel course_icon = new JLabel(new ImageIcon(courseImage));
		GridBagConstraints gbc_course_icon = new GridBagConstraints();
		gbc_course_icon.anchor = GridBagConstraints.NORTH;
		gbc_course_icon.insets = new Insets(0, 0, 0, 5);
		gbc_course_icon.gridx = 1;
		gbc_course_icon.gridy = 9;
		centerPanel.add(course_icon, gbc_course_icon);
		
		JComboBox<Object> course_combo = new JComboBox<Object>();
		course_combo.setModel(new DefaultComboBoxModel<Object>(new Read().getCourses()));
		GridBagConstraints gbc_course_combo = new GridBagConstraints();
		gbc_course_combo.gridwidth = 2;
		gbc_course_combo.insets = new Insets(0, 0, 0, 5);
		gbc_course_combo.fill = GridBagConstraints.BOTH;
		gbc_course_combo.gridx = 2;
		gbc_course_combo.gridy = 9;
		centerPanel.add(course_combo, gbc_course_combo);
		
		ImageIcon emailImageIcon = new ImageIcon(getClass().getResource("/resource/email.png"));
		Image emailImage = emailImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		JLabel email_icon = new JLabel(new ImageIcon(emailImage));
		GridBagConstraints gbc_email_icon = new GridBagConstraints();
		gbc_email_icon.insets = new Insets(0, 0, 0, 5);
		gbc_email_icon.anchor = GridBagConstraints.NORTHEAST;
		gbc_email_icon.gridx = 5;
		gbc_email_icon.gridy = 9;
		centerPanel.add(email_icon, gbc_email_icon);
		
		email_field = new JTextField();
		GridBagConstraints gbc_email_field = new GridBagConstraints();
		gbc_email_field.insets = new Insets(0, 0, 0, 5);
		gbc_email_field.gridwidth = 2;
		gbc_email_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_email_field.gridx = 6;
		gbc_email_field.gridy = 9;
		centerPanel.add(email_field, gbc_email_field);
		email_field.setColumns(10);
		
		JButton register_button = new JButton("Register");
		register_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DateModel<?> dateModel = dob_field.getModel();
					String fname = fname_field.getText();
					String lname = lname_field.getText();
					String pass = pass_field.getText();
					String cPass = cPass_field.getText();
					String dob = String.valueOf(dateModel.getYear()) + "-" + String.valueOf(dateModel.getMonth()+1) + "-" + String.valueOf(dateModel.getDay());
					Date date = Date.valueOf(dob);
					String address = address_field.getText() + ", " + city_field.getText();
					String gender = (String) gender_combo.getSelectedItem();
					String course = (String) course_combo.getSelectedItem();
					String email = email_field.getText();
					int id = new Read().getID("student") + 1;
					
					if (!pass.equals(cPass)) {
						new ErrorGUI("Password Did Not Matched...");
						return;
					} else if (pass.length() < 5) {
						new ErrorGUI("Password Cannot be less than 5 Character...");
						return;
					}
					
					new Create().addStudent(String.valueOf(id), fname, lname, date, address, gender, course, email, pass);
					new PopUpMessage("Successfully Created Account", "Your UserID is: " + id, "Your Password is: " + pass);
					
					contentPane.removeAll();
					loginComponent();
					contentPane.updateUI();
				} catch (Exception e2) {
					new ErrorGUI("All Fields are Mandatory.");
					e2.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_register_button = new GridBagConstraints();
		gbc_register_button.anchor = GridBagConstraints.NORTH;
		gbc_register_button.insets = new Insets(0, 0, 5, 5);
		gbc_register_button.gridx = 1;
		gbc_register_button.gridy = 0;
		bottomPanel.add(register_button, gbc_register_button);
	}
}
