package gui.Tutor;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import backend.Tutors;
import backend.Details.TutorDetails;
import database.TutorTableModels;
import exception.ErrorGUI;
import gui.LoginSignup;
import gui.PopUpMessage;
import utils.CustomFrame;
import utils.CustomJTable;
import utils.LoadImage;

public class TutorHomepage extends CustomFrame{
	
	private JPanel contentPane;
	
	private CardLayout frontCardLayout = new CardLayout();
	private CardLayout mainCardLayout = new CardLayout();
	private TutorTableModels tutorTableModels = new TutorTableModels();	
	private int tutId;

	/**
	 * Create the frame.
	 */
	public TutorHomepage(int uid) {
		this.tutId = uid;
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
			ArrayList<String> listData = new ArrayList<>(Arrays.asList("Homepage", "Courses", "Modules", "Report"));
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

	public JPanel coursePanel() {
		JPanel course_panel = new JPanel();
		course_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane course_scrollPane = new JScrollPane();
		course_panel.add(course_scrollPane, BorderLayout.CENTER);
		
		CustomJTable course_table = new CustomJTable();
		course_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		course_table.setAutoCreateRowSorter(true);
		course_scrollPane.setViewportView(course_table);
		course_table.setModel(tutorTableModels.getCourseDefaultTableModel());
		
		JPanel courseInfo_panel = new JPanel();
		course_panel.add(courseInfo_panel, BorderLayout.SOUTH);
		courseInfo_panel.setLayout(new BorderLayout(0, 0));
		
		JTextPane note_textpane = new JTextPane();
		note_textpane.setEnabled(false);
		note_textpane.setEditable(false);
		note_textpane.setText("Note:\r\nPress the Column Names For Sorting");
		note_textpane.setBackground((Color) null);
		courseInfo_panel.add(note_textpane, BorderLayout.CENTER);
		
		JPanel heading_panel = new JPanel();
		heading_panel.setBackground(new Color(128, 128, 128));
		heading_panel.setPreferredSize(new Dimension(10, 50));
		course_panel.add(heading_panel, BorderLayout.NORTH);
		heading_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel heading = new JLabel("List of Total Course Available");
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setFont(new Font("Tahoma", Font.BOLD, 20));
		heading_panel.add(heading);
		
		return course_panel;
	}
	
	public JPanel modulePanel() {
		TutorDetails tutor = new Tutors().getTutorDetails(tutId);
		JPanel module_panel = new JPanel();
		module_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel moduleInfo_panel = new JPanel();
		module_panel.add(moduleInfo_panel, BorderLayout.SOUTH);
		moduleInfo_panel.setLayout(new BorderLayout(0, 0));
		
		JTextPane note_textpane = new JTextPane();
		note_textpane.setEnabled(false);
		note_textpane.setEditable(false);
		note_textpane.setText("Note:\r\nSelect Tabs To See the Students That are Enrolled In Your Module\r\nPress the Column Names For Sorting");
		note_textpane.setBackground((Color) null);
		moduleInfo_panel.add(note_textpane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("New button");
		
		moduleInfo_panel.add(btnNewButton, BorderLayout.EAST);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		module_panel.add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane module1_scrollPane = new JScrollPane();
		tabbedPane.addTab("Module 1", null, module1_scrollPane, null);
		
		CustomJTable module1_table = new CustomJTable();
		module1_scrollPane.setViewportView(module1_table);
		
		
		JScrollPane module2_scrollPane = new JScrollPane();
		tabbedPane.addTab("Module 2", null, module2_scrollPane, null);
		
		CustomJTable module2_table = new CustomJTable();
		module2_scrollPane.setViewportView(module2_table);
		tabbedPane.setEnabledAt(1, true);
		
		
		JScrollPane module3_scrollPane = new JScrollPane();
		tabbedPane.addTab("Module 3", null, module3_scrollPane, null);
		
		CustomJTable module3_table = new CustomJTable();
		module3_scrollPane.setViewportView(module3_table);
		tabbedPane.setEnabledAt(2, true);
		
		
		JScrollPane module4_scrollPane = new JScrollPane();
		tabbedPane.addTab("Module 4", null, module4_scrollPane, null);
		
		CustomJTable module4_table = new CustomJTable();
		module4_scrollPane.setViewportView(module4_table);
		
		/**
		 * Ignoring All the IndexOutOfBoundsException While Trying to Read from the Array
		 */
		try {
			tutor.setModule1code(tutor.getModules().get(0));
			module1_table.setModel(tutorTableModels.getStudentDefaultTableModel(tutor));
			tutor.setModule1code(tutor.getModules().get(1));
			module2_table.setModel(tutorTableModels.getStudentDefaultTableModel(tutor));
			tutor.setModule1code(tutor.getModules().get(2));
			module3_table.setModel(tutorTableModels.getStudentDefaultTableModel(tutor));
			tutor.setModule1code(tutor.getModules().get(3));
			module4_table.setModel(tutorTableModels.getStudentDefaultTableModel(tutor));
		} catch (IndexOutOfBoundsException e) {}
		
		return module_panel;
	}
	
	public JPanel reportPanel() {
		TutorDetails tutor = new Tutors().getTutorDetails(tutId);
		JPanel report_panel = new JPanel();
		report_panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		report_panel.add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane report1_scrollpanel = new JScrollPane();
		tabbedPane.addTab("Module 1 Students", null, report1_scrollpanel, null);
		
		CustomJTable report1_table = new CustomJTable();
		report1_scrollpanel.setViewportView(report1_table);
		
		JScrollPane report2_scrollpanel = new JScrollPane();
		tabbedPane.addTab("Module 2 Students", null, report2_scrollpanel, null);
		
		CustomJTable report2_table = new CustomJTable();
		report2_scrollpanel.setViewportView(report2_table);
		
		JScrollPane report3_scrollpanel = new JScrollPane();
		tabbedPane.addTab("Module 3 Students", null, report3_scrollpanel, null);
		
		CustomJTable report3_table = new CustomJTable();
		report3_scrollpanel.setViewportView(report3_table);
		
		JScrollPane report4_scrollpanel = new JScrollPane();
		tabbedPane.addTab("Module 4 Students", null, report4_scrollpanel, null);
		
		CustomJTable report4_table = new CustomJTable();
		report4_scrollpanel.setViewportView(report4_table);
		
		JPanel reportCRUD_panel = new JPanel();
		report_panel.add(reportCRUD_panel, BorderLayout.SOUTH);
		reportCRUD_panel.setLayout(new BorderLayout(0, 0));
		
		JTextPane note_textpane = new JTextPane();
		note_textpane.setEditable(false);
		note_textpane.setEnabled(false);
		note_textpane.setText("Note:\r\nSelect Tabs To See the Students That are Enrolled In Your Module And Select Row From the Table to Assign Marks to Them\r\nPress the Column Names For Sorting");
		note_textpane.setBackground((Color) null);
		reportCRUD_panel.add(note_textpane, BorderLayout.CENTER);
		
		JPanel bottom_panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) bottom_panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(15);
		reportCRUD_panel.add(bottom_panel, BorderLayout.EAST);
		
		JLabel assignMarkIcon_button = new JLabel(new LoadImage("/resource/add-icon.png"));
		assignMarkIcon_button.setToolTipText("Assign Mark");
		bottom_panel.add(assignMarkIcon_button);
		assignMarkIcon_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					int activePanel = tabbedPane.getSelectedIndex();
					int row;
					int id;
					String moduleCode;
					
					if(activePanel==0) {
						row = report1_table.getSelectedRow();
						
						if(row<0) {
							new ErrorGUI("Please Select A Row From the Table");
							return;
						}
						
						id = (int) report1_table.getValueAt(row, 0);
						moduleCode = (String) report1_table.getValueAt(row, 3);

						float mark = Float.parseFloat(JOptionPane.showInputDialog("Marks"));
						
						boolean execute = new Tutors().assignMarks(mark, id, moduleCode);
						
						
						if (execute) {
							new PopUpMessage("Mark Assigned Successfully");
							tutor.setModule1code(tutor.getModules().get(0));
							report1_table.setModel(tutorTableModels.getReportDefaultTableModel(tutor));
						}
						
					} 
					if(activePanel==1) {
						row = report2_table.getSelectedRow();
						if(row<0) {
							new ErrorGUI("Please Select A Row From the Table");
							return;
						}
						id = (int) report2_table.getValueAt(row, 0);
						moduleCode = (String) report2_table.getValueAt(row, 3);
						float mark = Float.parseFloat(JOptionPane.showInputDialog("Marks"));
						
						boolean execute = new Tutors().assignMarks(mark, id, moduleCode);
						if (execute) {
							new PopUpMessage("Mark Assigned Successfully");
							tutor.setModule1code(tutor.getModules().get(1));
							report2_table.setModel(tutorTableModels.getReportDefaultTableModel(tutor));
						}
					} 
					if(activePanel==2) {
						row = report3_table.getSelectedRow();
						if(row<0) {
							new ErrorGUI("Please Select A Row From the Table");
							return;
						}
						id = (int) report3_table.getValueAt(row, 0);
						moduleCode = (String) report3_table.getValueAt(row, 3);
						float mark = Float.parseFloat(JOptionPane.showInputDialog("Marks"));
						
						boolean execute = new Tutors().assignMarks(mark, id, moduleCode);
						if (execute) {
							new PopUpMessage("Mark Assigned Successfully");
							tutor.setModule1code(tutor.getModules().get(2));
							report3_table.setModel(tutorTableModels.getReportDefaultTableModel(tutor));
						}
					} 
					if(activePanel==3) {
						row = report4_table.getSelectedRow();
						if(row<0) {
							new ErrorGUI("Please Select A Row From the Table");
							return;
						}
						id = (int) report4_table.getValueAt(row, 0);
						moduleCode = (String) report4_table.getValueAt(row, 3);
						float mark = Float.parseFloat(JOptionPane.showInputDialog("Marks"));
						
						boolean execute = new Tutors().assignMarks(mark, id, moduleCode);
						if (execute) {
							new PopUpMessage("Mark Assigned Successfully");
							tutor.setModule1code(tutor.getModules().get(3));
							report4_table.setModel(tutorTableModels.getReportDefaultTableModel(tutor));
						}
					}
				} catch (NumberFormatException | NullPointerException e2) {
					if (e2 instanceof NumberFormatException) {
						new ErrorGUI("Please Input A Decimal or Float Value");						
					} else if (e2 instanceof NullPointerException) {
						e2.printStackTrace();
					}
				}
			}
		});
		
		/**
		 * Ignoring All the IndexOutOfBoundsException While Trying to Read from the Array
		 */
		try {
			tutor.setModule1code(tutor.getModules().get(0));
			report1_table.setModel(tutorTableModels.getReportDefaultTableModel(tutor));
			tutor.setModule1code(tutor.getModules().get(1));
			report2_table.setModel(tutorTableModels.getReportDefaultTableModel(tutor));
			tutor.setModule1code(tutor.getModules().get(2));
			report3_table.setModel(tutorTableModels.getReportDefaultTableModel(tutor));
			tutor.setModule1code(tutor.getModules().get(3));
			report4_table.setModel(tutorTableModels.getReportDefaultTableModel(tutor));
		} catch (IndexOutOfBoundsException e) {}
		
		return report_panel;
	}
}
