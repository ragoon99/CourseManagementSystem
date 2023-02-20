package gui.Admin;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import backend.Courses;
import backend.Modules;
import backend.Details.ModulesDetails;
import gui.PopUpMessage;

public class ModuleForm extends Modules {
	private String moduleCode;
	private boolean update;
	
	public ModuleForm() {
		this(null, false);
	}
	/**
	 * Create the dialog.
	 */
	public ModuleForm(String moduleCode, boolean update) {
		this.moduleCode = moduleCode;
		this.update = update;
		
		new ModuleFormGUI();
	}
	
	private class ModuleFormGUI extends JDialog {
		private final JPanel contentPanel = new JPanel();
		private JTextField moduleCode_field;
		private JTextField moduleName_field;
		private JComboBox semesterComboBox;
		private String selectedCourse;
		private JButton okButton;
		private JLabel coursePriority_label;
		
		private ModulesDetails modulesDetails = new ModulesDetails();
		private Courses courses = new Courses();
		
		private ModuleFormGUI() {
			if(update) {
				updateModuleForm();
			} else {
				addModuleForm();
			}
		}
		
 		private void addModuleForm() {
			setTitle("Add Module");
			setResizable(false);
			setBounds(100, 100, 530, 209);
			getContentPane().setLayout(new BorderLayout());
			setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			
			contentPanel.setBorder(new TitledBorder(null, " ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[]{64, 87, 0, 49, 0, 100, 0};
			gbl_contentPanel.rowHeights = new int[]{26, 0, 19, 0, 0};
			gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			contentPanel.setLayout(gbl_contentPanel);

			JLabel moduleCode_label = new JLabel("Module Code : ");
			GridBagConstraints gbc_moduleCode_label = new GridBagConstraints();
			gbc_moduleCode_label.insets = new Insets(0, 0, 5, 5);
			gbc_moduleCode_label.anchor = GridBagConstraints.EAST;
			gbc_moduleCode_label.gridx = 0;
			gbc_moduleCode_label.gridy = 1;
			contentPanel.add(moduleCode_label, gbc_moduleCode_label);
			
			moduleCode_field = new JTextField();
			moduleCode_field.setPreferredSize(new Dimension(20, 19));
			GridBagConstraints gbc_moduleCode_field = new GridBagConstraints();
			gbc_moduleCode_field.gridwidth = 2;
			gbc_moduleCode_field.fill = GridBagConstraints.BOTH;
			gbc_moduleCode_field.insets = new Insets(0, 0, 5, 5);
			gbc_moduleCode_field.gridx = 1;
			gbc_moduleCode_field.gridy = 1;
			contentPanel.add(moduleCode_field, gbc_moduleCode_field);
			moduleCode_field.setColumns(10);

			JLabel moduleName_label = new JLabel("Module Name : ");
			GridBagConstraints gbc_moduleName_label = new GridBagConstraints();
			gbc_moduleName_label.insets = new Insets(0, 0, 5, 5);
			gbc_moduleName_label.anchor = GridBagConstraints.EAST;
			gbc_moduleName_label.gridx = 3;
			gbc_moduleName_label.gridy = 1;
			contentPanel.add(moduleName_label, gbc_moduleName_label);

			moduleName_field = new JTextField();
			GridBagConstraints gbc_moduleName_field = new GridBagConstraints();
			gbc_moduleName_field.gridwidth = 2;
			gbc_moduleName_field.insets = new Insets(0, 0, 5, 0);
			gbc_moduleName_field.fill = GridBagConstraints.BOTH;
			gbc_moduleName_field.gridx = 4;
			gbc_moduleName_field.gridy = 1;
			contentPanel.add(moduleName_field, gbc_moduleName_field);
			moduleName_field.setColumns(10);
			
			JLabel courseField_label = new JLabel("Course Field : ");
			GridBagConstraints gbc_courseField_label = new GridBagConstraints();
			gbc_courseField_label.anchor = GridBagConstraints.EAST;
			gbc_courseField_label.insets = new Insets(0, 0, 0, 5);
			gbc_courseField_label.gridx = 0;
			gbc_courseField_label.gridy = 3;
			contentPanel.add(courseField_label, gbc_courseField_label);
			
			JComboBox<Object> courseField_comboBox = new JComboBox();
			courseField_comboBox.setModel(new DefaultComboBoxModel<>(courses.courseList().keySet().toArray()));
			courseField_comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedCourse = courseField_comboBox.getSelectedItem().toString();
					semesterComboBox.setModel(new DefaultComboBoxModel<>(courses.getTotalSemesters(selectedCourse).toArray()));
				}
			});
			courseField_comboBox.setPreferredSize(new Dimension(100, 21));
			GridBagConstraints gbc_courseField_comboBox = new GridBagConstraints();
			gbc_courseField_comboBox.insets = new Insets(0, 0, 0, 5);
			gbc_courseField_comboBox.fill = GridBagConstraints.BOTH;
			gbc_courseField_comboBox.gridx = 1;
			gbc_courseField_comboBox.gridy = 3;
			contentPanel.add(courseField_comboBox, gbc_courseField_comboBox);

			JLabel semester_label = new JLabel("Semester : ");
			GridBagConstraints gbc_semester_label = new GridBagConstraints();
			gbc_semester_label.anchor = GridBagConstraints.EAST;
			gbc_semester_label.insets = new Insets(0, 0, 0, 5);
			gbc_semester_label.gridx = 2;
			gbc_semester_label.gridy = 3;
			contentPanel.add(semester_label, gbc_semester_label);

			semesterComboBox= new JComboBox();
			semesterComboBox.setPreferredSize(new Dimension(50, 21));
			GridBagConstraints gbc_semester_comboBox = new GridBagConstraints();
			gbc_semester_comboBox.fill = GridBagConstraints.BOTH;
			gbc_semester_comboBox.insets = new Insets(0, 0, 0, 5);
			gbc_semester_comboBox.gridx = 3;
			gbc_semester_comboBox.gridy = 3;
			contentPanel.add(semesterComboBox, gbc_semester_comboBox);

			coursePriority_label = new JLabel("Course Type : ");
			GridBagConstraints gbc_coursePriority_label = new GridBagConstraints();
			gbc_coursePriority_label.anchor = GridBagConstraints.EAST;
			gbc_coursePriority_label.insets = new Insets(0, 0, 0, 5);
			gbc_coursePriority_label.gridx = 4;
			gbc_coursePriority_label.gridy = 3;
			contentPanel.add(coursePriority_label, gbc_coursePriority_label);

			JComboBox<Object> moduleType_combo = new JComboBox();
			moduleType_combo.setModel(new DefaultComboBoxModel(new String[] {"Compulsory", "Optional"}));
			GridBagConstraints gbc_moduleType_combo = new GridBagConstraints();
			gbc_moduleType_combo.fill = GridBagConstraints.BOTH;
			gbc_moduleType_combo.gridx = 5;
			gbc_moduleType_combo.gridy = 3;
			contentPanel.add(moduleType_combo, gbc_moduleType_combo);
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			okButton = new JButton("Add Module");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modulesDetails.setModuleCode(moduleCode_field.getText());
					modulesDetails.setModuleName(moduleName_field.getText());
					modulesDetails.setSemester((int) semesterComboBox.getSelectedItem());
					modulesDetails.setCourseField(courses.courseList().get(courseField_comboBox.getSelectedItem().toString()));
					modulesDetails.setModuleType(moduleType_combo.getSelectedItem().toString());
					
					boolean execute = createModule(modulesDetails);		
					
					if (execute) {
						new PopUpMessage("Added Module Successfully");
						
						dispose();
					}
				}
			});
			buttonPane.add(okButton);
			
			setVisible(true);
		}
		
		private void updateModuleForm() {
			setTitle("Update Module");
			setResizable(false);
			setBounds(100, 100, 520, 209);
			getContentPane().setLayout(new BorderLayout());
			setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			contentPanel.setBorder(new TitledBorder(null, " ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[]{64, 87, 0, 49, 0, 100, 0};
			gbl_contentPanel.rowHeights = new int[]{26, 0, 19, 0, 0};
			gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			contentPanel.setLayout(gbl_contentPanel);
			
			JLabel moduleCode_label = new JLabel("Module Code : ");
			GridBagConstraints gbc_moduleCode_label = new GridBagConstraints();
			gbc_moduleCode_label.insets = new Insets(0, 0, 5, 5);
			gbc_moduleCode_label.anchor = GridBagConstraints.EAST;
			gbc_moduleCode_label.gridx = 0;
			gbc_moduleCode_label.gridy = 1;
			contentPanel.add(moduleCode_label, gbc_moduleCode_label);
			
			moduleCode_field = new JTextField();
			moduleCode_field.setPreferredSize(new Dimension(20, 19));
			GridBagConstraints gbc_moduleCode_field = new GridBagConstraints();
			gbc_moduleCode_field.gridwidth = 2;
			gbc_moduleCode_field.fill = GridBagConstraints.BOTH;
			gbc_moduleCode_field.insets = new Insets(0, 0, 5, 5);
			gbc_moduleCode_field.gridx = 1;
			gbc_moduleCode_field.gridy = 1;
			contentPanel.add(moduleCode_field, gbc_moduleCode_field);
			moduleCode_field.setColumns(10);
			
			JLabel moduleName_label = new JLabel("Module Name : ");
			GridBagConstraints gbc_moduleName_label = new GridBagConstraints();
			gbc_moduleName_label.insets = new Insets(0, 0, 5, 5);
			gbc_moduleName_label.anchor = GridBagConstraints.EAST;
			gbc_moduleName_label.gridx = 3;
			gbc_moduleName_label.gridy = 1;
			contentPanel.add(moduleName_label, gbc_moduleName_label);
			
			moduleName_field = new JTextField();
			GridBagConstraints gbc_moduleName_field = new GridBagConstraints();
			gbc_moduleName_field.gridwidth = 2;
			gbc_moduleName_field.insets = new Insets(0, 0, 5, 0);
			gbc_moduleName_field.fill = GridBagConstraints.BOTH;
			gbc_moduleName_field.gridx = 4;
			gbc_moduleName_field.gridy = 1;
			contentPanel.add(moduleName_field, gbc_moduleName_field);
			moduleName_field.setColumns(10);
			
			JLabel courseField_label = new JLabel("Course Field : ");
			GridBagConstraints gbc_courseField_label = new GridBagConstraints();
			gbc_courseField_label.anchor = GridBagConstraints.EAST;
			gbc_courseField_label.insets = new Insets(0, 0, 0, 5);
			gbc_courseField_label.gridx = 0;
			gbc_courseField_label.gridy = 3;
			contentPanel.add(courseField_label, gbc_courseField_label);
			
			JComboBox<Object> courseField_comboBox = new JComboBox();
			courseField_comboBox.setModel(new DefaultComboBoxModel<>(courses.courseList().keySet().toArray()));
			courseField_comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedCourse = courseField_comboBox.getSelectedItem().toString();
					semesterComboBox.setModel(new DefaultComboBoxModel<>(courses.getTotalSemesters(selectedCourse).toArray()));
				}
			});
			courseField_comboBox.setPreferredSize(new Dimension(100, 21));
			GridBagConstraints gbc_courseField_comboBox = new GridBagConstraints();
			gbc_courseField_comboBox.insets = new Insets(0, 0, 0, 5);
			gbc_courseField_comboBox.fill = GridBagConstraints.BOTH;
			gbc_courseField_comboBox.gridx = 1;
			gbc_courseField_comboBox.gridy = 3;
			contentPanel.add(courseField_comboBox, gbc_courseField_comboBox);
		
			JLabel semester_label = new JLabel("Semester : ");
			GridBagConstraints gbc_semester_label = new GridBagConstraints();
			gbc_semester_label.anchor = GridBagConstraints.EAST;
			gbc_semester_label.insets = new Insets(0, 0, 0, 5);
			gbc_semester_label.gridx = 2;
			gbc_semester_label.gridy = 3;
			contentPanel.add(semester_label, gbc_semester_label);
					
			semesterComboBox = new JComboBox();
			semesterComboBox.setPreferredSize(new Dimension(50, 21));
			GridBagConstraints gbc_semester_comboBox = new GridBagConstraints();
			gbc_semester_comboBox.fill = GridBagConstraints.BOTH;
			gbc_semester_comboBox.insets = new Insets(0, 0, 0, 5);
			gbc_semester_comboBox.gridx = 3;
			gbc_semester_comboBox.gridy = 3;
			contentPanel.add(semesterComboBox, gbc_semester_comboBox);
				
			coursePriority_label = new JLabel("Course Type : ");
			GridBagConstraints gbc_coursePriority_label = new GridBagConstraints();
			gbc_coursePriority_label.anchor = GridBagConstraints.EAST;
			gbc_coursePriority_label.insets = new Insets(0, 0, 0, 5);
			gbc_coursePriority_label.gridx = 4;
			gbc_coursePriority_label.gridy = 3;
			contentPanel.add(coursePriority_label, gbc_coursePriority_label);
		
			JComboBox<Object> moduleType_combo = new JComboBox();
			moduleType_combo.setModel(new DefaultComboBoxModel(new String[] {"Compulsory", "Optional"}));
			GridBagConstraints gbc_moduleType_combo = new GridBagConstraints();
			gbc_moduleType_combo.fill = GridBagConstraints.BOTH;
			gbc_moduleType_combo.gridx = 5;
			gbc_moduleType_combo.gridy = 3;
			contentPanel.add(moduleType_combo, gbc_moduleType_combo);
					
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			okButton = new JButton("Update Module");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modulesDetails.setModuleName(moduleName_field.getText());
					modulesDetails.setSemester((int) semesterComboBox.getSelectedItem());
					modulesDetails.setModuleType(moduleType_combo.getSelectedItem().toString());
					modulesDetails.setCourseField(courses.courseList().get(courseField_comboBox.getSelectedItem().toString()));
					
					boolean execute = updateModule(modulesDetails, moduleCode_field.getText());
					
					if(execute) {
						new PopUpMessage("Updated Module Details Successfully");
						
						dispose();
					}
				}
			});
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);

			modulesDetails = getModulesDetails(moduleCode);
			
			moduleCode_field.setText(modulesDetails.getModuleCode());
			moduleName_field.setText(modulesDetails.getModuleName());
			courseField_comboBox.setSelectedItem(modulesDetails.getCourseFieldName());
			semesterComboBox.setSelectedItem(modulesDetails.getSemester());

			setVisible(true);
		}
		
	}

	
		
}
