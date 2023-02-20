package gui.Student;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import backend.Students;
import backend.Details.StudentDetails;
import database.StudentTableModels;
import exception.ErrorGUI;
import gui.LoginSignup;
import utils.CustomFrame;
import utils.CustomJTable;
import utils.LoadImage;

public class StudentHomepage extends CustomFrame{
	
	private CustomJTable report_table;
	
	private JPanel contentPane;
	
	private CardLayout frontCardLayout = new CardLayout();
	private CardLayout mainCardLayout = new CardLayout();
	private StudentTableModels studentTableModels = new StudentTableModels();
	
	private int stdId;

	/**
	 * Create the frame.
	 */
	public StudentHomepage(int uid) {
		this.stdId = uid;
		setTitle("Couse Management System");
		setMinimumSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(800, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel mid_panel = new JPanel();
		mid_panel.setBackground(new Color(255, 255, 255));
		contentPane.add(mid_panel, BorderLayout.CENTER);
		
		JPanel left_panel = new JPanel();
		left_panel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, new Color(0, 0, 0)), null));
		contentPane.add(left_panel, BorderLayout.WEST);
		left_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel lTop_panel = new JPanel();
		lTop_panel.setPreferredSize(new Dimension(150, 100));
		left_panel.add(lTop_panel, BorderLayout.NORTH);
		lTop_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("CMS");
		lblNewLabel.setBackground(new Color(192, 192, 192));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Brush Script MT", Font.PLAIN, 26));
		lTop_panel.add(lblNewLabel);
		
		JPanel lCenter_panel = new JPanel();
		left_panel.add(lCenter_panel, BorderLayout.CENTER);
		lCenter_panel.setLayout(new BorderLayout(0, 0));
		
		JList<Object> list = new JList<Object>();
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		list.setBackground(null);
		list.setFixedCellHeight(50);
		
		list.setModel(new AbstractListModel<Object>() {
			ArrayList<String> listData = new ArrayList<>(Arrays.asList("Homepage", "Tutors", "Courses", "Modules", "Report"));
			Object values[] = listData.toArray();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		list.setSelectedIndex(0);
		lCenter_panel.add(list);
		
		JPanel lBottom_panel = new JPanel();
		left_panel.add(lBottom_panel, BorderLayout.SOUTH);
		
		JLabel setting_icon = new JLabel(new LoadImage("/resource/settings-icon.png", 24, 24));
		lBottom_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setting_icon.setToolTipText("Settings");
		setting_icon.setPreferredSize(new Dimension(35, 35));
		setting_icon.setHorizontalTextPosition(SwingConstants.CENTER);
		lBottom_panel.add(setting_icon);
		
		JLabel help_icon = new JLabel(new LoadImage("/resource/help-icon.png", 24, 24));
		help_icon.setToolTipText("Help");
		help_icon.setPreferredSize(new Dimension(35, 35));
		lBottom_panel.add(help_icon);
		
		JLabel logout_icon = new JLabel(new LoadImage("/resource/logout-icon.png", 24, 24));
		logout_icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Confirm Logout?", "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					new LoginSignup();
					dispose();
				}
			}
		});
		logout_icon.setPreferredSize(new Dimension(35, 35));
		logout_icon.setToolTipText("Logout");
		logout_icon.setHorizontalAlignment(SwingConstants.CENTER);
		lBottom_panel.add(logout_icon);
		mid_panel.setLayout(frontCardLayout);
		
		JPanel mainPanel = new JPanel();
		mid_panel.add(mainPanel, "mainPanel");
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel title_panel = new JPanel();
		mainPanel.add(title_panel, BorderLayout.NORTH);
		title_panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		title_panel.setPreferredSize(new Dimension(10, 100));
		title_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel title_label = new JLabel("");
		title_label.setFont(new Font("Tahoma", Font.BOLD, 32));
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_panel.add(title_label);
		
		JPanel center_panel = new JPanel();
		mainPanel.add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(mainCardLayout);
		center_panel.add(this.tutorPanel(), "tutor_panel");
		center_panel.add(this.coursePanel(), "course_panel");
		center_panel.add(this.modulePanel(), "module_panel");
		center_panel.add(this.reportPanel(), "report_panel");
		
		mid_panel.add(this.homepagePanel(), "homepage");	
		
		list.setSelectedIndex(0);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue().equals("Homepage")) {
					frontCardLayout.show(mid_panel, "homepage");
				}
				if (list.getSelectedValue().equals("Courses")) {
					title_label.setText("Courses");
					frontCardLayout.show(mid_panel, "mainPanel");
					mainCardLayout.show(center_panel, "course_panel");
				}
				if (list.getSelectedValue().equals("Tutors")) {
					title_label.setText("Tutor");
					frontCardLayout.show(mid_panel, "mainPanel");
					mainCardLayout.show(center_panel, "tutor_panel");
				}
				if (list.getSelectedValue().equals("Modules")) {
					title_label.setText("Modules");
					mainCardLayout.show(center_panel, "module_panel");
					frontCardLayout.show(mid_panel, "mainPanel");
				}
				if (list.getSelectedValue().equals("Report")) {
					title_label.setText("Reports");
					mainCardLayout.show(center_panel, "report_panel");
					frontCardLayout.show(mid_panel, "mainPanel");
				}
				
				center_panel.updateUI();
			}
		});
		
		frontCardLayout.show(mid_panel, "homepage");
		
		setVisible(true);
	}
	
	private JPanel homepagePanel() {
		JPanel homepage = new JPanel();
		homepage.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		homepage.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome To Course Management System");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 11;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		return homepage;
	}

	private JPanel tutorPanel() {
		StudentDetails studentDetails = new Students().getStudentDetails(stdId);
		ArrayList<String> coursesEnrolled = new Students().enrolledModule(studentDetails);
		
		JPanel tutor_panel = new JPanel();
		tutor_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane tutor_scrollPane = new JScrollPane();
		tutor_panel.add(tutor_scrollPane, BorderLayout.CENTER);
		
		CustomJTable tutor_table = new CustomJTable();
		tutor_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tutor_table.setAutoCreateRowSorter(true);
		tutor_scrollPane.setViewportView(tutor_table);
		tutor_table.setModel(studentTableModels.getTutorDefaultTableModel(coursesEnrolled));
		
		JPanel panel = new JPanel();
		tutor_panel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTextPane note_textpane = new JTextPane();
		note_textpane.setText("Note:\r\nPress the Column Names For Sorting");
		note_textpane.setBackground((Color) null);
		panel.add(note_textpane, BorderLayout.CENTER);
		
		return tutor_panel;
	}

	public JPanel coursePanel() {
		StudentDetails studentDetails = new Students().getStudentDetails(stdId);
		JPanel course_panel = new JPanel();
		course_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane course_scrollPane = new JScrollPane();
		course_panel.add(course_scrollPane, BorderLayout.CENTER);
		
		CustomJTable course_table = new CustomJTable();
		course_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		course_table.setAutoCreateRowSorter(true);
		course_scrollPane.setViewportView(course_table);
		course_table.setModel(studentTableModels.getCourseDefaultTableModel());
		
		JPanel courseCRUD_panel = new JPanel();
		course_panel.add(courseCRUD_panel, BorderLayout.SOUTH);
		courseCRUD_panel.setLayout(new BorderLayout(0, 0));
		
		JTextPane note_textpane = new JTextPane();
		note_textpane.setText("Note:\r\nPress the Column Names For Sorting");
		note_textpane.setBackground((Color) null);
		courseCRUD_panel.add(note_textpane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setForeground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		course_panel.add(panel, BorderLayout.NORTH);
		
		JLabel enrolledCourse_label = new JLabel("Your Enrolled Course : ");
		enrolledCourse_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(enrolledCourse_label);
		
		JLabel enrolledCourse = new JLabel(studentDetails.getCourseEnrolled());
		enrolledCourse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(enrolledCourse);
		
		return course_panel;
	}
	
	public JPanel modulePanel() {
		StudentDetails studentDetails = new Students().getStudentDetails(stdId);
		
		JPanel module_panel = new JPanel();
		module_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel moduleCRUD_panel = new JPanel();
		module_panel.add(moduleCRUD_panel, BorderLayout.SOUTH);
		moduleCRUD_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(15);
		moduleCRUD_panel.add(panel, BorderLayout.EAST);
		
		JLabel addIcon_button = new JLabel(new LoadImage("/resource/add-icon.png"));
		addIcon_button.setToolTipText("Enroll Module");
		panel.add(addIcon_button);
		
		JTextPane note_textpane = new JTextPane();
		note_textpane.setText("Note:\r\nSelect Row From Enrollable Modules To Enroll To That Module\r\nPress the Column Names For Sorting");
		note_textpane.setBackground((Color) null);
		moduleCRUD_panel.add(note_textpane, BorderLayout.CENTER);
		
		JPanel table_panel = new JPanel();
		module_panel.add(table_panel, BorderLayout.CENTER);
		GridBagLayout gbl_table_panel = new GridBagLayout();
		gbl_table_panel.columnWidths = new int[]{462, 0};
		gbl_table_panel.rowHeights = new int[]{0, 0, 0};
		gbl_table_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_table_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		table_panel.setLayout(gbl_table_panel);
		
		JPanel enrollable_module = new JPanel();
		GridBagConstraints gbc_enrollable_module = new GridBagConstraints();
		gbc_enrollable_module.fill = GridBagConstraints.BOTH;
		gbc_enrollable_module.insets = new Insets(0, 0, 5, 0);
		gbc_enrollable_module.gridx = 0;
		gbc_enrollable_module.gridy = 0;
		table_panel.add(enrollable_module, gbc_enrollable_module);
		enrollable_module.setLayout(new BorderLayout(0, 0));
		
		JScrollPane enrollable_ScrollPane = new JScrollPane();
		enrollable_module.add(enrollable_ScrollPane, BorderLayout.CENTER);
		
		CustomJTable enrollable_table = new CustomJTable();
		enrollable_ScrollPane.setViewportView(enrollable_table);
		enrollable_table.setModel(studentTableModels.getEnrollableCourseTableModel(studentDetails));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		enrollable_module.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Enrollable Modules");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		JPanel enrolled_panel = new JPanel();
		GridBagConstraints gbc_enrolled_panel = new GridBagConstraints();
		gbc_enrolled_panel.fill = GridBagConstraints.BOTH;
		gbc_enrolled_panel.gridx = 0;
		gbc_enrolled_panel.gridy = 1;
		table_panel.add(enrolled_panel, gbc_enrolled_panel);
		enrolled_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane enrolled_scrollPane = new JScrollPane();
		enrolled_panel.add(enrolled_scrollPane, BorderLayout.CENTER);
		
		CustomJTable enrolledModule_table = new CustomJTable();
		enrolled_scrollPane.setViewportView(enrolledModule_table);
		enrolledModule_table.setModel(studentTableModels.getEnrolledCourseTableModel(studentDetails));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 128));
		enrolled_panel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Enrolled Modules");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_2.add(lblNewLabel_3);
		
		addIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = enrollable_table.getSelectedRow();
				if (row < 0) {
					new ErrorGUI("Please Select From the Enrollable Module Table");
					return;
				}
				
				String seletedCourse = (String) enrollable_table.getValueAt(row, 0);
				
				new Students().enrollModule(studentDetails, seletedCourse);
				
				enrolledModule_table.setModel(studentTableModels.getEnrolledCourseTableModel(studentDetails));
			}
		});
		
		return module_panel;
	}
	
	public JPanel reportPanel() {
		StudentDetails studentDetails = new Students().getStudentDetails(stdId);
		
		JPanel report_panel = new JPanel();
		GridBagLayout gbl_report_panel = new GridBagLayout();
		gbl_report_panel.columnWidths = new int[]{462, 0};
		gbl_report_panel.rowHeights = new int[]{0, 0};
		gbl_report_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_report_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		report_panel.setLayout(gbl_report_panel);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		report_panel.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane report_scrollPane = new JScrollPane();
		panel.add(report_scrollPane);
		
		report_table = new CustomJTable();
		report_scrollPane.setViewportView(report_table);
		report_table.setModel(studentTableModels.getReportDefaultTableModel(studentDetails));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{130, 47, 0};
		gbl_panel_2.rowHeights = new int[]{30, 30, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Full Name : ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel fullName_field = new JLabel(studentDetails.getFirstName() + " " + studentDetails.getLastName());
		GridBagConstraints gbc_fullName_field = new GridBagConstraints();
		gbc_fullName_field.anchor = GridBagConstraints.WEST;
		gbc_fullName_field.insets = new Insets(0, 0, 5, 0);
		gbc_fullName_field.gridx = 1;
		gbc_fullName_field.gridy = 0;
		panel_2.add(fullName_field, gbc_fullName_field);
		
		JLabel lblNewLabel_3 = new JLabel("Student ID : ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1; 
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel studentID_field = new JLabel(String.valueOf(studentDetails.getStdId()));
		GridBagConstraints gbc_studentID_field = new GridBagConstraints();
		gbc_studentID_field.anchor = GridBagConstraints.WEST;
		gbc_studentID_field.gridx = 1;
		gbc_studentID_field.gridy = 1;
		panel_2.add(studentID_field, gbc_studentID_field);
		
		return report_panel;
	}
}
