package gui.Admin;

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

import backend.Courses;
import backend.Modules;
import backend.Students;
import backend.Tutors;
import backend.Details.TutorDetails;
import database.AdminTableModels;
import exception.ErrorGUI;
import gui.LoginSignup;
import gui.PopUpMessage;
import utils.CustomFrame;
import utils.CustomJTable;
import utils.LoadImage;

public class AdminHomepage extends CustomFrame{
	
	private CustomJTable report_table;
	
	private JPanel contentPane;
	
	private CardLayout frontCardLayout = new CardLayout();
	private CardLayout mainCardLayout = new CardLayout();
	private AdminTableModels adminTableModels = new AdminTableModels();
	private CustomJTable module_table;

	/**
	 * Create the frame.
	 */
	public AdminHomepage(int uid) {
		setTitle("Couse Management System");
		setMinimumSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 600);
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
			ArrayList<String> listData = new ArrayList<>(Arrays.asList("Homepage", "Students", "Tutors", "Courses", "Modules", "Report"));
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
		
		center_panel.add(this.studentPanel(), "student_panel");
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
				if (list.getSelectedValue().equals("Students")) {
					frontCardLayout.show(mid_panel, "mainPanel");
					title_label.setText("Students");
					mainCardLayout.show(center_panel, "student_panel");
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
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		CustomJTable activityLog_table = new CustomJTable();
		scrollPane.setViewportView(activityLog_table);
		
		activityLog_table.setModel(adminTableModels.getUserActivityLog());
		
		JLabel userActivity_label = new JLabel("User Activity");
		userActivity_label.setPreferredSize(new Dimension(57, 50));
		userActivity_label.setHorizontalAlignment(SwingConstants.CENTER);
		userActivity_label.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.add(userActivity_label, BorderLayout.NORTH);
		
		return homepage;
	}
	
	private JPanel studentPanel() {
		JPanel student_panel = new JPanel();
		student_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane student_scrollPane = new JScrollPane();
		student_panel.add(student_scrollPane, BorderLayout.CENTER);
		
		CustomJTable student_table = new CustomJTable();
		student_table.setAutoCreateRowSorter(true);
		student_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		student_scrollPane.setViewportView(student_table);
		student_panel.setVisible(true);
		student_table.setModel(adminTableModels.getStudentDefaultTableModel());
		
		JPanel studentCRUD_panel = new JPanel();
		student_panel.add(studentCRUD_panel, BorderLayout.SOUTH);
		studentCRUD_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(15);
		panel.setPreferredSize(new Dimension(150, 10));
		studentCRUD_panel.add(panel, BorderLayout.EAST);
		
		JLabel addIcon_button = new JLabel(new LoadImage("/resource/add-icon.png"));
		addIcon_button.setToolTipText("Add Student");
		panel.add(addIcon_button);
		
		JLabel updateIcon_button = new JLabel(new LoadImage("/resource/edit-icon.png", 30, 30));
		updateIcon_button.setToolTipText("Edit Student Information");
		panel.add(updateIcon_button);
		
		
		JLabel removeIcon_button = new JLabel(new LoadImage("/resource/remove-icon.png"));
		removeIcon_button.setToolTipText("Delete Student Information");
		panel.add(removeIcon_button);
		
		JTextPane note_textpane = new JTextPane();
		note_textpane.setEnabled(false);
		note_textpane.setEditable(false);
		note_textpane.setBackground(null);
		note_textpane.setText("Note:\r\nSelect Row From Above Table to Edit or Delete\r\nPress the Column Names For Sorting");
		studentCRUD_panel.add(note_textpane, BorderLayout.CENTER);
		
		removeIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = student_table.getSelectedRow();
				
				if (row < 0) {
					new ErrorGUI("Please Select A Student Detail from Above To Edit");
					return;
				}
				
				int stdId = (int) student_table.getValueAt(row, 0);
				
				int result = JOptionPane.showConfirmDialog(null, "Confirm Delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					if(new Students().removeStudent(stdId)) {
						new PopUpMessage("Successfully Deleted");
					} else {
						new ErrorGUI("Error Occured While Deleting Data");
					}
				}
				
				student_table.setModel(adminTableModels.getStudentDefaultTableModel());
			}
		});
		updateIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = student_table.getSelectedRow();
				
				if (row < 0) {
					new ErrorGUI("Please Select A Student Detail from Above To Edit");
					return;
				}
				
				int stdId = (int) student_table.getValueAt(row, 0);
				new StudentForm(stdId, true);
				student_table.setModel(adminTableModels.getStudentDefaultTableModel());
			}
		});
		addIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new StudentForm();
				student_table.setModel(adminTableModels.getStudentDefaultTableModel());
			}
		});
		
		return student_panel;
	}

	private JPanel tutorPanel() {
		JPanel tutor_panel = new JPanel();
		tutor_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel CRUD_panel = new JPanel();
		tutor_panel.add(CRUD_panel, BorderLayout.SOUTH);
		CRUD_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(250, 10));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(15);
		CRUD_panel.add(panel_1, BorderLayout.EAST);
		
		JLabel addIcon_button = new JLabel(new LoadImage("/resource/add-icon.png"));
		addIcon_button.setToolTipText("Add Tutor");
		panel_1.add(addIcon_button);
		
		JLabel updateIcon_button = new JLabel(new LoadImage("/resource/edit-icon.png", 30, 30));
		updateIcon_button.setToolTipText("Edit Tutor Information");
		panel_1.add(updateIcon_button);
		
		JLabel registerTutorIcon_button = new JLabel("Regsiter Tutor");
		panel_1.add(registerTutorIcon_button);
		
		JLabel removeIcon_button = new JLabel(new LoadImage("/resource/remove-icon.png"));
		removeIcon_button.setToolTipText("Delete Tutor Information");
		panel_1.add(removeIcon_button);
		
		JTextPane note_textpane = new JTextPane();
		note_textpane.setEnabled(false);
		note_textpane.setEditable(false);
		note_textpane.setText("Note:\r\nSelect Row From Above Table to Edit or Delete\r\nPress the Column Names For Sorting");
		note_textpane.setBackground((Color) null);
		CRUD_panel.add(note_textpane, BorderLayout.CENTER);
		
		JPanel tables_panel = new JPanel();
		tutor_panel.add(tables_panel, BorderLayout.CENTER);
		GridBagLayout gbl_tables_panel = new GridBagLayout();
		gbl_tables_panel.columnWidths = new int[]{337, 252, 0};
		gbl_tables_panel.rowHeights = new int[]{0, 0, 0};
		gbl_tables_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_tables_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		tables_panel.setLayout(gbl_tables_panel);
		
		JPanel registeredTutor_panel = new JPanel();
		registeredTutor_panel.setBackground(new Color(128, 128, 128));
		GridBagConstraints gbc_registeredTutor_panel = new GridBagConstraints();
		gbc_registeredTutor_panel.fill = GridBagConstraints.BOTH;
		gbc_registeredTutor_panel.insets = new Insets(0, 0, 5, 0);
		gbc_registeredTutor_panel.gridx = 1;
		gbc_registeredTutor_panel.gridy = 0;
		tables_panel.add(registeredTutor_panel, gbc_registeredTutor_panel);
		registeredTutor_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		registeredTutor_panel.add(scrollPane_1, BorderLayout.CENTER);
		
		CustomJTable registeredTutor_table = new CustomJTable();
		scrollPane_1.setViewportView(registeredTutor_table);
		registeredTutor_table.setModel(adminTableModels.getRegisteredTutor());
		
		JLabel lblNewLabel_1 = new JLabel("Registered Tutors");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		registeredTutor_panel.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel selectTutor_panel = new JPanel();
		selectTutor_panel.setBackground(new Color(128, 128, 128));
		GridBagConstraints gbc_selectTutor_panel = new GridBagConstraints();
		gbc_selectTutor_panel.insets = new Insets(0, 0, 5, 5);
		gbc_selectTutor_panel.fill = GridBagConstraints.BOTH;
		gbc_selectTutor_panel.gridx = 0;
		gbc_selectTutor_panel.gridy = 0;
		tables_panel.add(selectTutor_panel, gbc_selectTutor_panel);
		selectTutor_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane tutor_scrollPane = new JScrollPane();
		selectTutor_panel.add(tutor_scrollPane);
		tutor_scrollPane.setPreferredSize(new Dimension(2, 50));
		
		CustomJTable tutor_table = new CustomJTable();
		tutor_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tutor_table.setAutoCreateRowSorter(true);
		tutor_scrollPane.setViewportView(tutor_table);
		tutor_table.setModel(adminTableModels.getTutorDefaultTableModel());
		
		JLabel lblNewLabel_2 = new JLabel("Tutor List");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		selectTutor_panel.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel selectCourse_panel = new JPanel();
		selectCourse_panel.setBackground(new Color(128, 128, 128));
		GridBagConstraints gbc_selectCourse_panel = new GridBagConstraints();
		gbc_selectCourse_panel.gridwidth = 2;
		gbc_selectCourse_panel.insets = new Insets(0, 0, 0, 5);
		gbc_selectCourse_panel.fill = GridBagConstraints.BOTH;
		gbc_selectCourse_panel.gridx = 0;
		gbc_selectCourse_panel.gridy = 1;
		tables_panel.add(selectCourse_panel, gbc_selectCourse_panel);
		selectCourse_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		selectCourse_panel.add(scrollPane);
		
		module_table = new CustomJTable();
		module_table.setModel(adminTableModels.getModuleDefaultTableModel());
		scrollPane.setViewportView(module_table);
		
		JLabel lblNewLabel_3 = new JLabel("Modules List");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		selectCourse_panel.add(lblNewLabel_3, BorderLayout.NORTH);
		removeIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = tutor_table.getSelectedRow();
				
				if (row < 0) {
					new ErrorGUI("Please Select A Student Detail from Above To Edit");
					return;
				}
				
				int tutId = (int) tutor_table.getValueAt(row, 0);
				
				int result = JOptionPane.showConfirmDialog(null, "Confirm Delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					if(new Tutors().removeTutor(tutId)) {
						new PopUpMessage("Successfully Deleted");
					} else {
						new ErrorGUI("Error Occured While Deleting Data");
					}
				}
				
				tutor_table.setModel(adminTableModels.getTutorDefaultTableModel());
			}
		});
		updateIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = tutor_table.getSelectedRow();
				
				if (row < 0) {
					new ErrorGUI("Please Select A Row From The Table");
					return;
				}

				int id = (int) tutor_table.getValueAt(row, 0);
				
				new TutorForm(id, true);
				
				tutor_table.setModel(adminTableModels.getTutorDefaultTableModel());
			}
		});
		addIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new TutorForm();
				
				tutor_table.setModel(adminTableModels.getTutorDefaultTableModel());
			}
		});
		registerTutorIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int tutorRow = tutor_table.getSelectedRow();				
				if (tutorRow < 0) {
					new ErrorGUI("Please Select A Row From Tutor Table Before Registring");
					return;
				}
				int tutorId = (int) tutor_table.getValueAt(tutorRow, 0);
				
				int moduleRow = module_table.getSelectedRow();
				if (moduleRow < 0) {
					new ErrorGUI("Please Select A Row From Module Table Before Registring");
					return;
				}
				String moduleCode = (String) module_table.getValueAt(moduleRow, 0);
				
				TutorDetails tutorDetails = new TutorDetails();
				tutorDetails.setTutId(tutorId);
				tutorDetails.setModule1code(moduleCode);
				
				boolean execute = new Tutors().enrollModule(tutorDetails);
				
				if (execute) {
					new PopUpMessage("Tutor Registered Successfully");
					
					registeredTutor_table.setModel(adminTableModels.getRegisteredTutor());
					tutor_table.setModel(adminTableModels.getTutorDefaultTableModel());
				}
			}
		});
		
		return tutor_panel;
	}

	public JPanel coursePanel() {
		JPanel course_panel = new JPanel();
		course_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane course_scrollPane = new JScrollPane();
		course_panel.add(course_scrollPane, BorderLayout.CENTER);
		
		CustomJTable course_table = new CustomJTable();
		course_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		course_table.setAutoCreateRowSorter(true);
		course_scrollPane.setViewportView(course_table);
		course_table.setModel(adminTableModels.getCourseDefaultTableModel());
		
		JPanel courseCRUD_panel = new JPanel();
		course_panel.add(courseCRUD_panel, BorderLayout.SOUTH);
		courseCRUD_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(15);
		courseCRUD_panel.add(panel, BorderLayout.EAST);
		
		JLabel addIcon_button = new JLabel(new LoadImage("/resource/add-icon.png"));
		addIcon_button.setToolTipText("Add Course");
		panel.add(addIcon_button);
		
		JLabel updateIcon_button = new JLabel(new LoadImage("/resource/edit-icon.png", 30, 30));
		updateIcon_button.setToolTipText("Update Course");
		panel.add(updateIcon_button);
		
		JLabel cancelIcon_button = new JLabel(new LoadImage("/resource/cancel-icon.png"));
		cancelIcon_button.setToolTipText("Set Course Availibility");
		panel.add(cancelIcon_button);
		
		JLabel removeIcon_button = new JLabel(new LoadImage("/resource/remove-icon.png"));
		removeIcon_button.setToolTipText("Delete Course");
		panel.add(removeIcon_button);
		
		JTextPane note_textpane = new JTextPane();
		note_textpane.setEditable(false);
		note_textpane.setEnabled(false);
		note_textpane.setText("Note:\r\nSelect Row From Above Table to Edit or Delete\r\nPress the Column Names For Sorting");
		note_textpane.setBackground((Color) null);
		courseCRUD_panel.add(note_textpane, BorderLayout.CENTER);
		removeIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = course_table.getSelectedRow();
				
				if (row < 0) {
					new ErrorGUI("Please Select A Student Detail from Above To Edit");
					return;
				}
				
				String courseCode = (String) course_table.getValueAt(row, 0);
				
				int result = JOptionPane.showConfirmDialog(null, "Confirm Delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					if(new Courses().removeCourse(courseCode)) {
						new PopUpMessage("Successfully Deleted");
					} else {
						new ErrorGUI("Error Occured While Deleting Data");
					}
				}
				
				course_table.setModel(adminTableModels.getCourseDefaultTableModel());
			}
		});
		cancelIcon_button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				int row = course_table.getSelectedRow();
				
				if (row < 0) {
					new ErrorGUI("Please Select A Row From The Table");
					return;
				}

				String id = (String) course_table.getValueAt(row, 0);
				
				boolean tOf = course_table.getValueAt(row, 5).equals("Available");
				
				String dialogMessage = (tOf ?  "Confirm Cancel Course?" : "Make Course Available?");
				String popUpMessage = (tOf ?  "Course Cancelled" : "Course Re-Enabled");
				
				int result = JOptionPane.showConfirmDialog(null, dialogMessage, "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					if(new Courses().setCourseAvailibiliy(id)) {
						new PopUpMessage(popUpMessage);
					} else {
						new ErrorGUI("Error Occured While Deleting Data");
					}
				}
				course_table.setModel(adminTableModels.getCourseDefaultTableModel());
			}
		});
		updateIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = course_table.getSelectedRow();
				
				if (row < 0) {
					new ErrorGUI("Please Select A Row From The Table");
					return;
				}

				String id = (String) course_table.getValueAt(row, 0);
				
				new CourseForm(id, true);
				
				course_table.setModel(adminTableModels.getCourseDefaultTableModel());
			}
		});
		addIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new CourseForm();
				
				course_table.setModel(adminTableModels.getCourseDefaultTableModel());
			}
		});
		
		return course_panel;
	}
	
	public JPanel modulePanel() {
		JPanel module_panel = new JPanel();
		module_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane module_scrollPane = new JScrollPane();
		module_panel.add(module_scrollPane, BorderLayout.CENTER);
		
		CustomJTable module_table = new CustomJTable();
		module_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		module_scrollPane.setViewportView(module_table);
		module_table.setModel(adminTableModels.getModuleDefaultTableModel());
		
		JPanel moduleCRUD_panel = new JPanel();
		module_panel.add(moduleCRUD_panel, BorderLayout.SOUTH);
		moduleCRUD_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(15);
		moduleCRUD_panel.add(panel, BorderLayout.EAST);
		
		JLabel addIcon_button = new JLabel(new LoadImage("/resource/add-icon.png"));
		addIcon_button.setToolTipText("Add Module");
		panel.add(addIcon_button);
		
		JLabel updateIcon_button = new JLabel(new LoadImage("/resource/edit-icon.png", 30, 30));
		updateIcon_button.setToolTipText("Update Module");
		panel.add(updateIcon_button);
		
		JLabel removeIcon_button = new JLabel(new LoadImage("/resource/remove-icon.png"));
		removeIcon_button.setToolTipText("Remove Module");
		panel.add(removeIcon_button);
		
		JTextPane note_textpane = new JTextPane();
		note_textpane.setEnabled(false);
		note_textpane.setEditable(false);
		note_textpane.setText("Note:\r\nSelect Row From Above Table to Edit or Delete\r\nPress the Column Names For Sorting");
		note_textpane.setBackground((Color) null);
		moduleCRUD_panel.add(note_textpane, BorderLayout.CENTER);
		removeIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = module_table.getSelectedRow();
				
				if (row < 0) {
					new ErrorGUI("Please Select A Student Detail from Above To Edit");
					return;
				}
				
				String moduleCode = (String) module_table.getValueAt(row, 0);
				
				int result = JOptionPane.showConfirmDialog(null, "Confirm Delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					if(new Modules().removeModule(moduleCode)) {
						new PopUpMessage("Successfully Deleted");
					} else {
						new ErrorGUI("Error Occured While Deleting Data");
					}
				}
				
				module_table.setModel(adminTableModels.getModuleDefaultTableModel());
			}
		});
		updateIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = module_table.getSelectedRow();
				
				if (row < 0) {
					new ErrorGUI("Please Select A Row From The Table");
					return;
				}

				String courseCode = (String) module_table.getValueAt(row, 0);
				
				new ModuleForm(courseCode, true);
				
				module_table.setModel(adminTableModels.getModuleDefaultTableModel());
			}
		});
		addIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new ModuleForm();
				
				module_table.setModel(adminTableModels.getModuleDefaultTableModel());
			}
		});
		
		module_table.setAutoCreateRowSorter(true);
		
		return module_panel;
	}
	
	public JPanel reportPanel() {
		JPanel report_panel = new JPanel();
		report_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane report_scrollPane = new JScrollPane();
		report_panel.add(report_scrollPane, BorderLayout.CENTER);
		
		report_table = new CustomJTable();
		report_scrollPane.setViewportView(report_table);
		report_table.setModel(adminTableModels.getReportDefaultTableModel());
		
		return report_panel;
	}
}
