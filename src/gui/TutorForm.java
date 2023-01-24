package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import db.Create;
import db.Read;
import exception.ErrorGUI;
import utils.DateLabelFormatter;
import utils.JTextFieldLimit;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.Properties;

import javax.swing.JTextField;

public class TutorForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public TutorForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 681, 588);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTextField fname_field;
		JTextField address_field;
		JTextField pass_field;
		JTextField lname_field;
		JTextField cPass_field;
		JTextField city_field;
		
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
		gbl_bottomPanel.columnWidths = new int[]{291, 85, 85, 0};
		gbl_bottomPanel.rowHeights = new int[]{36, 36, 0, 0};
		gbl_bottomPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bottomPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		bottomPanel.setLayout(gbl_bottomPanel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{45, 67, 150, 33, 34, 0, 150, 0};
		gbl_centerPanel.rowHeights = new int[]{35, 25, 31, 25, 27, 25, 28, 0, 24, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_lname_label.gridx = 5;
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
		gbc_lname_field.insets = new Insets(0, 0, 5, 0);
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
		gbc_city_field.insets = new Insets(0, 0, 5, 0);
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
		gbc_cPass_field.insets = new Insets(0, 0, 5, 0);
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
		gbc_gender_icon.gridx = 4;
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
		gbc_gender_combo.insets = new Insets(0, 0, 5, 0);
		gbc_gender_combo.fill = GridBagConstraints.BOTH;
		gbc_gender_combo.gridx = 5;
		gbc_gender_combo.gridy = 7;
		centerPanel.add(gender_combo, gbc_gender_combo);
		
		ImageIcon courseImageIcon = new ImageIcon(getClass().getResource("/resource/online-course.png"));
		Image courseImage = courseImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		
		ImageIcon emailImageIcon = new ImageIcon(getClass().getResource("/resource/email.png"));
		Image emailImage = emailImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		
		JLabel email_label = new JLabel("Email :");
		email_label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_email_label = new GridBagConstraints();
		gbc_email_label.anchor = GridBagConstraints.WEST;
		gbc_email_label.insets = new Insets(0, 0, 5, 5);
		gbc_email_label.gridx = 2;
		gbc_email_label.gridy = 8;
		centerPanel.add(email_label, gbc_email_label);
		JLabel email_icon = new JLabel(new ImageIcon(emailImage));
		GridBagConstraints gbc_email_icon = new GridBagConstraints();
		gbc_email_icon.insets = new Insets(0, 0, 0, 5);
		gbc_email_icon.anchor = GridBagConstraints.NORTH;
		gbc_email_icon.gridx = 1;
		gbc_email_icon.gridy = 9;
		centerPanel.add(email_icon, gbc_email_icon);
		
		JTextField email_field = new JTextField();
		GridBagConstraints gbc_email_field = new GridBagConstraints();
		gbc_email_field.insets = new Insets(0, 0, 0, 5);
		gbc_email_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_email_field.gridx = 2;
		gbc_email_field.gridy = 9;
		centerPanel.add(email_field, gbc_email_field);
		email_field.setColumns(10);
		
		JButton register_button = new JButton("Add Tutor");
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
					String email = email_field.getText();
					int id = new Read().getID("tutor") + 1;
					
					if (!pass.equals(cPass)) {
						new ErrorGUI("Password Did Not Matched...");
						return;
					} else if (pass.length() < 5) {
						new ErrorGUI("Password Cannot be less than 5 Character...");
						return;
					}
					
					new Create().addTutor(String.valueOf(id), fname, lname, date, address, gender, email, pass);
					new PopUpMessage("Successfully Addded Tutor", "Tutor ID is: " + id, "Tutor Password is: " + pass);
					dispose();
				} catch (Exception e2) {
					new ErrorGUI("All Fields are Mandatory.");
					e2.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_register_button = new GridBagConstraints();
		gbc_register_button.insets = new Insets(0, 0, 5, 5);
		gbc_register_button.gridx = 1;
		gbc_register_button.gridy = 1;
		bottomPanel.add(register_button, gbc_register_button);
	}

}
