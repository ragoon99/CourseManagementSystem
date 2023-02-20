package gui.Tutor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ReportFrom extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReportFrom dialog = new ReportFrom();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReportFrom() {
		setResizable(false);
		setBounds(100, 100, 328, 423);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel center_panel = new JPanel();
			contentPanel.add(center_panel, BorderLayout.CENTER);
			GridBagLayout gbl_center_panel = new GridBagLayout();
			gbl_center_panel.columnWidths = new int[]{30, 0, 0, 0, 0, 30, 0, 0};
			gbl_center_panel.rowHeights = new int[]{30, 50, 50, 50, 0, 50, 0};
			gbl_center_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_center_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			center_panel.setLayout(gbl_center_panel);
			{
				JLabel stdId_label = new JLabel("Student ID : ");
				GridBagConstraints gbc_stdId_label = new GridBagConstraints();
				gbc_stdId_label.anchor = GridBagConstraints.EAST;
				gbc_stdId_label.insets = new Insets(0, 0, 5, 5);
				gbc_stdId_label.gridx = 1;
				gbc_stdId_label.gridy = 1;
				center_panel.add(stdId_label, gbc_stdId_label);
			}
			{
				JLabel stdId_placeholder = new JLabel("placeholder");
				GridBagConstraints gbc_stdId_placeholder = new GridBagConstraints();
				gbc_stdId_placeholder.insets = new Insets(0, 0, 5, 5);
				gbc_stdId_placeholder.gridx = 2;
				gbc_stdId_placeholder.gridy = 1;
				center_panel.add(stdId_placeholder, gbc_stdId_placeholder);
			}
			{
				JLabel stdFirstName_label = new JLabel("Student First Name : ");
				GridBagConstraints gbc_stdFirstName_label = new GridBagConstraints();
				gbc_stdFirstName_label.anchor = GridBagConstraints.EAST;
				gbc_stdFirstName_label.insets = new Insets(0, 0, 5, 5);
				gbc_stdFirstName_label.gridx = 1;
				gbc_stdFirstName_label.gridy = 2;
				center_panel.add(stdFirstName_label, gbc_stdFirstName_label);
			}
			{
				JLabel stdFirstName_placeholder = new JLabel("placeholder");
				GridBagConstraints gbc_stdFirstName_placeholder = new GridBagConstraints();
				gbc_stdFirstName_placeholder.insets = new Insets(0, 0, 5, 5);
				gbc_stdFirstName_placeholder.gridx = 2;
				gbc_stdFirstName_placeholder.gridy = 2;
				center_panel.add(stdFirstName_placeholder, gbc_stdFirstName_placeholder);
			}
			{
				JLabel stdLastName_label = new JLabel("Student Last Name : ");
				GridBagConstraints gbc_stdLastName_label = new GridBagConstraints();
				gbc_stdLastName_label.anchor = GridBagConstraints.EAST;
				gbc_stdLastName_label.insets = new Insets(0, 0, 5, 5);
				gbc_stdLastName_label.gridx = 1;
				gbc_stdLastName_label.gridy = 3;
				center_panel.add(stdLastName_label, gbc_stdLastName_label);
			}
			{
				JLabel stdLastName_placeholder = new JLabel("placeholder");
				GridBagConstraints gbc_stdLastName_placeholder = new GridBagConstraints();
				gbc_stdLastName_placeholder.insets = new Insets(0, 0, 5, 5);
				gbc_stdLastName_placeholder.gridx = 2;
				gbc_stdLastName_placeholder.gridy = 3;
				center_panel.add(stdLastName_placeholder, gbc_stdLastName_placeholder);
			}
			{
				JLabel grade_label = new JLabel("Grade : ");
				GridBagConstraints gbc_grade_label = new GridBagConstraints();
				gbc_grade_label.anchor = GridBagConstraints.EAST;
				gbc_grade_label.insets = new Insets(0, 0, 0, 5);
				gbc_grade_label.gridx = 1;
				gbc_grade_label.gridy = 5;
				center_panel.add(grade_label, gbc_grade_label);
			}
			{
				textField = new JTextField();
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(0, 0, 0, 5);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 2;
				gbc_textField.gridy = 5;
				center_panel.add(textField, gbc_textField);
				textField.setColumns(10);
			}
		}
		{
			JPanel bottom_panel = new JPanel();
			FlowLayout fl_bottom_panel = (FlowLayout) bottom_panel.getLayout();
			fl_bottom_panel.setVgap(20);
			fl_bottom_panel.setHgap(20);
			contentPanel.add(bottom_panel, BorderLayout.SOUTH);
			{
				JButton assignMark_button = new JButton("Assign Mark");
				bottom_panel.add(assignMark_button);
			}
			{
				JButton cancel_button = new JButton("Cancel");
				cancel_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				bottom_panel.add(cancel_button);
			}
		}
		{
			JPanel top_panel = new JPanel();
			contentPanel.add(top_panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("Assign Mark");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
				top_panel.add(lblNewLabel);
			}
		}
	}

}
